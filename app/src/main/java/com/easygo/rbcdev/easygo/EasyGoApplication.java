package com.easygo.rbcdev.easygo;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

import java.util.HashMap;

/**
 * Created by rbcdev on 15-10-05.
 */
public class EasyGoApplication extends Application {

    private HashMap<String, Object> mAppData;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppData = new HashMap<String, Object>();
        ActiveAndroid.initialize(this);
    }

    public Object getData(String key) {
        return mAppData.get(key);
    }

    public void setData(String key, Object value) {
       mAppData.put(key,value);
    }
}
