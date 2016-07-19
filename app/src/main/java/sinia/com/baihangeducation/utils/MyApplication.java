package sinia.com.baihangeducation.utils;

import android.app.Application;

/**
 * Created by 忧郁的眼神 on 2016/7/15.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

}
