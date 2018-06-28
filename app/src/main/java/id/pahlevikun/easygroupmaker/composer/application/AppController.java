package id.pahlevikun.easygroupmaker.composer.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class AppController extends MultiDexApplication {

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mInstance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized AppController getmInstance() {
        return mInstance;
    }

    @SuppressLint("StaticFieldLeak")
    private static AppController enableMultiDex;
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    public AppController() {
        enableMultiDex = this;
    }

    public static AppController getEnableMultiDexApp() {
        return enableMultiDex;
    }


}