package com.rescueph.task;

import android.os.AsyncTask;
import android.util.Log;

import com.rescueph.MainActivity;
import com.rescueph.model.Messages;
import com.rescueph.model.User;
import com.rescueph.utils.OnTaskCompleted;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jm on 24/07/2016.
 */
public class HttpRegisterTask extends AsyncTask<Void,Void,User> {

    private static final String TAG = "HttpRegisterTask";
    private static User user;
    private OnTaskCompleted listener;
    private String url = "http://"+ MainActivity.IP+":8080/rescueph/rest/members/add";

    public HttpRegisterTask(User user, OnTaskCompleted listener) {
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
            user = restTemplate.postForObject(url, user, User.class);
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
            user = null;
        }
        return user;
    }

    public static User getUser() {
        return user;
    }
}
