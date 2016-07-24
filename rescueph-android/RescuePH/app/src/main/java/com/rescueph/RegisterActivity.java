package com.rescueph;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rescueph.model.User;
import com.rescueph.task.HttpRegisterTask;
import com.rescueph.utils.OnTaskCompleted;


public class RegisterActivity extends AppCompatActivity implements OnTaskCompleted{

    public static final int REG = 0x3000C;
    private EditText name;
    private EditText contact;
    private EditText pass;
    private EditText email;
    private EditText confirm_pass;
    private EditText photoId;
    private AlertDialog alertDialog;
    private HttpRegisterTask task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
        alertDialog.setTitle("Registration");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        getSupportActionBar().hide();

        email = (EditText)findViewById(R.id.email_tb);
        name = (EditText)findViewById(R.id.name_tb);
        contact = (EditText)findViewById(R.id.contact_tb);
        pass = (EditText)findViewById(R.id.pass_tb);
        confirm_pass = (EditText) findViewById(R.id.confirm_pass_tb);
        photoId = (EditText) findViewById(R.id.validid_tb);

        Button regButton = (Button) findViewById(R.id.button_reg);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().equals("")&&!name.getText().toString().equals("")
                        &&!contact.getText().toString().equals("")&&!pass.getText().toString().equals("")
                        &&!confirm_pass.getText().toString().equals("")){
                    if(confirm_pass.getText().toString().equals(pass.getText().toString())){
                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setContactnumber(contact.getText().toString());
                        user.setPassword(pass.getText().toString());
                        user.setFullname(name.getText().toString());
                        task = new HttpRegisterTask(user,RegisterActivity.this);
                        task.execute();

                    }else{
                        alertDialog.setMessage(getResources().getString(R.string.password_unmatch));
                        alertDialog.show();
                    }
                }else{
                    alertDialog.setMessage(getResources().getString(R.string.incomplete_reg));
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    public void onTaskCompleted() {
        if(task.getUser()!=null){
            email.setText("");
            contact.setText("");
            pass.setText("");
            confirm_pass.setText("");
            photoId.setText("");
            name.setText("");
            alertDialog.setMessage(getResources().getString(R.string.success_reg));
        }else{
            alertDialog.setMessage(getResources().getString(R.string.fail_reg));
        }
        alertDialog.show();
    }
}
