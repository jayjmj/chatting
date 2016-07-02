package com.jayjmj.chatting;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by jiseung on 16. 7. 1.
 */
public class AppApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
