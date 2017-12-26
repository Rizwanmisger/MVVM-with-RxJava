package com.example.a10620225.mvvmsample.network;

import com.example.a10620225.mvvmsample.model.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 10620225 on 12/20/2017.
 */

public interface UsersService {

    @GET
    Observable<UserResponse> fetchUsers(@Url String url);
}
