package com.example.a10620225.mvvmsample.app;

import android.app.Application;

import com.example.a10620225.mvvmsample.dagger.AppComponent;
import com.example.a10620225.mvvmsample.dagger.AppModule;
import com.example.a10620225.mvvmsample.dagger.DaggerAppComponent;

/**
 * Created by 10620225 on 12/20/2017.
 */

public class MyApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.appComponent = initDagger(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AppComponent initDagger(MyApplication application){
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(application))
                    .build();
    }
}
