package com.example.jasper.capstone.service_classes;

/**
 * Created by Jasper on 1 Sep 2017.
 */

import com.example.jasper.capstone.model_classes.UserClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @Headers("Content-Type: application/json")
    @GET("user/users")
    Call<List<UserClass>> getAllUsers();

    @GET("user/signin/{email}/{password}")
    Call<UserClass> getUserByEmailPassword(@Path("email") String email, @Path("password") String userpass);

    @GET("user/{user_id}")
    Call<UserClass> getUserById(@Path("user_id") int user_id);

    @POST("user/add")
    Call<UserClass> addUser(@Body UserClass user);

    @POST("user/update/{user_id}")
    Call<Void> editUser(@Path("user_id") int user_id, @Body UserClass user);

}
