package com.example.a10620225.mvvmsample.dagger;

import com.example.a10620225.mvvmsample.network.UsersService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.a10620225.mvvmsample.app.Constants.BASE_URL;

/**
 * Created by 10620225 on 12/20/2017.
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    UsersService provideUsersService(Retrofit retrofit){
        return retrofit.create(UsersService.class);
    }

}
