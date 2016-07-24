package com.rescueph.task;

import android.os.AsyncTask;
import android.util.Log;

import com.rescueph.MainActivity;
import com.rescueph.model.Messages;
import com.rescueph.utils.OnTaskCompleted;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jm on 23/07/2016.
 */
public class HttpUpdateMsgTask extends AsyncTask<Void,Void,Messages> {

    private static Messages messages;
    private OnTaskCompleted listener;
    private String url = "http://"+ MainActivity.IP+":8080/rescueph/rest/messages/";
    private static final String TAG = "HttpSendingMsgTask";

    public HttpUpdateMsgTask(Messages messages, OnTaskCompleted listener) {
        super();
        this.messages = messages;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Messages messages) {
        super.onPostExecute(messages);
        listener.onTaskCompleted();
    }

    @Override
    protected Messages doInBackground(Void... params) {
        //instantiates httpclient to make request
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
            messages = restTemplate.getForObject(url+messages.getMessageid(),Messages.class);
        }catch (Exception e){
            messages = null;
            Log.d(TAG ,e.getLocalizedMessage());
        }
        return messages;
    }

    public static Messages getMessages() {
        return messages;
    }
}
