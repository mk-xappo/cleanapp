package de.xappo.test_android_med_unlimited101.main;

import android.app.Application;


/**
 * Android Main Application
 */
public class MyApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com"))
                .build();

    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }


}
