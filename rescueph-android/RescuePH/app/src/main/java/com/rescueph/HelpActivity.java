package com.rescueph;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rescueph.model.Messages;
import com.rescueph.task.HttpSendMsgTask;
import com.rescueph.task.HttpUpdateMsgTask;
import com.rescueph.utils.AutoCompleteAdapter;
import com.rescueph.utils.FallbackLocationTracker;
import com.rescueph.utils.OnTaskCompleted;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;

public class HelpActivity extends AppCompatActivity implements OnMapReadyCallback, OnTaskCompleted {


    public static final int HELP = 0x2000B;
    private static final String TAG = "HelpActivity";
    private FallbackLocationTracker locationTracker;
    public static final String USER_ID_STR = "userId";
    public static final String FULL_NAME_STR = "fullname";
    private GoogleMap mMap;
    private Location location;
    private double latitude;
    private double longitude;
    private String userId;
    private String fullname;
    private AutoCompleteTextView message_tb;
    private final static String currLocPreTxt = "Current Location : ";
    private AlertDialog alertDialog;
    private HttpSendMsgTask task;
    private HttpUpdateMsgTask updatetask;
    private Boolean hasSentMsg;
    private String msgId;
    //private AutoCompleteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_msg);
        getSupportActionBar().hide();
        hasSentMsg = false;
        //adapter = new AutoCompleteAdapter(this,)
        alertDialog = new AlertDialog.Builder(HelpActivity.this).create();
        alertDialog.setTitle("Alert!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        SupportMapFragment mapFragment;
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        userId = getIntent().getStringExtra(USER_ID_STR);
        fullname = getIntent().getStringExtra(FULL_NAME_STR);
        message_tb = (AutoCompleteTextView)findViewById(R.id.message_tb);
        ImageView fab = (ImageView) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!message_tb.getText().toString().equals("")){
                    Messages messages = new Messages();
                    messages.setMessage(message_tb.getText().toString());
                    messages.setUserid(userId);
                    messages.setFullname(fullname);
                    Log.d(TAG,"latitude :" +latitude);
                    Log.d(TAG,"longitude :" + longitude);
                    messages.setXcoord(latitude);
                    messages.setYcoord(longitude);
                    task = new HttpSendMsgTask(messages,HelpActivity.this);
                    task.execute();
                }else{
                    alertDialog.setMessage(getResources().getString(R.string.message_req));
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    public void onTaskCompleted() {
        Messages tempMsg = new Messages();
        if(hasSentMsg){
            if(updatetask.getMessages()!=null) {
                Messages updateMessage = updatetask.getMessages();
                if(updateMessage.getStatus() == 1){
                    hasSentMsg = false;
                    alertDialog.setMessage(getResources().getString(R.string.help_coming));
                    alertDialog.show();
                }else{
                    tempMsg.setMessageid(msgId);
                    updatetask = new HttpUpdateMsgTask(tempMsg,HelpActivity.this);
                    updatetask.execute();
                }
            }
        }else if(task.getMessages()!=null){
            msgId = task.getMessages().getMessageid();
            message_tb.setText("");
            hasSentMsg = true;
            tempMsg.setMessageid(msgId);
            updatetask = new HttpUpdateMsgTask(tempMsg,HelpActivity.this);
            updatetask.execute();
            alertDialog.setMessage(getResources().getString(R.string.message_sent_sux));
            alertDialog.show();
        }else{
            alertDialog.setMessage(getResources().getString(R.string.message_sent_fail));
            alertDialog.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(onMapClickListener);
        locationTracker = new FallbackLocationTracker(HelpActivity.this);
        locationTracker.start();

        if(locationTracker.hasLocation()){
            location = locationTracker.getLocation();
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            locationTracker.stop();
        }else if(locationTracker.hasPossiblyStaleLocation()){
            location = locationTracker.getPossiblyStaleLocation();
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            locationTracker.stop();
        }else{
            latitude = 0.0;
            longitude = 0.0;
        }


        // Add a marker in current, and move the camera.
        //locationText.setText(currLocPreTxt + latitude + ", "+longitude);
        LatLng current = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(current).title("Current Location."));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current,16.0f));
    }


    private GoogleMap.OnMapClickListener onMapClickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location."));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16.0f));
            latitude = latLng.latitude;
            longitude = latLng.longitude;
        }
    };

}
