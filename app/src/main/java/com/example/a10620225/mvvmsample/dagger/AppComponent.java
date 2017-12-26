package com.example.a10620225.mvvmsample.dagger;

import com.example.a10620225.mvvmsample.view.Home;
import com.example.a10620225.mvvmsample.viewModel.UserViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 10620225 on 12/20/2017.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(UserViewModel target);
    void inject(Home target);
}
