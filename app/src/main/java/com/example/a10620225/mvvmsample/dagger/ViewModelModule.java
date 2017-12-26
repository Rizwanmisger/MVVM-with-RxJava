package com.example.a10620225.mvvmsample.dagger;

import android.content.Context;

import com.example.a10620225.mvvmsample.databinding.ItemUserBinding;
import com.example.a10620225.mvvmsample.viewModel.ItemUserViewModel;
import com.example.a10620225.mvvmsample.viewModel.UserViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 10620225 on 12/21/2017.
 */
@Module
public class ViewModelModule {
    @Provides
    public UserViewModel provideUserViewModel(Context context){
        return new UserViewModel(context);
    }
}
