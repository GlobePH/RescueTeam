package com.rescueph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.rescueph.model.User;
import com.rescueph.task.HttpLoginTask;
import com.rescueph.utils.OnTaskCompleted;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class MainActivity extends Activity implements OnTaskCompleted{

    public static File userFile = new File(Environment.getExternalStorageDirectory()+"/rescuePH/"+"user.txt");
    private String userid;
    private String fullname;
    private HttpLoginTask task;
    private Boolean islogged;
    public static final String IP = "192.168.171.235";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(checkIfHasUserLogged()!=null&&checkIfHasUserLogged()){
            loadHelpActivity();
        }else{
            loadLoginActivity();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == LoginActivity.LOGIN){
                userid = data.getStringExtra(HelpActivity.USER_ID_STR);
                fullname = data.getStringExtra(HelpActivity.FULL_NAME_STR);
                loadHelpActivity();
            }
        }else if(resultCode == RESULT_FIRST_USER){
            if(requestCode == LoginActivity.LOGIN){
                loadRegisterActivity();
            }
        }else if(resultCode == RESULT_CANCELED){
            if(requestCode!= LoginActivity.LOGIN){
                loadLoginActivity();
            }else{
                finish();
            }
        }
    }

    private void loadLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivityForResult(intent,LoginActivity.LOGIN);
    }

    private void loadHelpActivity(){
        Intent intent = new Intent(this,HelpActivity.class);
        intent.putExtra(HelpActivity.USER_ID_STR,userid);
        intent.putExtra(HelpActivity.FULL_NAME_STR,fullname);
        startActivityForResult(intent,HelpActivity.HELP);
    }

    private void loadRegisterActivity(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent,RegisterActivity.REG);
    }

    private Boolean checkIfHasUserLogged(){

        if(userFile.exists()){
            try {
                FileReader fileReader = new FileReader(userFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String user="",pass="", ret="";
                int i = 0;
                while ((ret = bufferedReader.readLine())!=null){
                    if(i == 0){
                        user = ret;
                    }else {
                        pass = ret;
                    }
                    i++;
                }
                fileReader.close();
                bufferedReader.close();

                if (user != "") {
                    User userObj = new User();
                    userObj.setEmail(user);
                    userObj.setPassword(pass);
                    task =  new HttpLoginTask(userObj,MainActivity.this);
                    task.execute();
                    Thread.sleep(2000);
                    if(task.getUser()!=null){
                        islogged = task.getUser().getIsloggedin();
                        if(islogged){
                            userid = task.getUser().getUserid();
                            fullname = task.getUser().getFullname();
                        }
                    }else{
                        islogged = false;
                    }
                }
            }catch (Exception e){

            }
        }

        return islogged;
    }

    @Override
    public void onTaskCompleted() {
        //do nothing
    }
}
