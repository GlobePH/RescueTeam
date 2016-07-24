package com.rescueph.task;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.rescueph.MainActivity;
import com.rescueph.model.User;
import com.rescueph.utils.OnTaskCompleted;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jm on 23/07/2016.
 */
public class HttpLoginTask extends AsyncTask<Void,Void,User> {

    private static User user;
    private OnTaskCompleted listener;
    private String url = "http://"+ MainActivity.IP+":8080/rescueph/rest/login/";
    private static final String TAG = "HttpLoginTask";

    public HttpLoginTask(User user, OnTaskCompleted listener) {
        super();
        this.user = user;
        this.listener = listener;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        listener.onTaskCompleted();
    }

    @Override
    protected User doInBackground(Void... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
            url = url+user.getEmail()+"/"+user.getPassword();
            user = restTemplate.getForObject(url,User.class);
        }catch (Exception e){
            user = null;
            Log.d(TAG,e.getLocalizedMessage());
        }
        return user;
    }

    public static User getUser() {
        return user;
    }
}
