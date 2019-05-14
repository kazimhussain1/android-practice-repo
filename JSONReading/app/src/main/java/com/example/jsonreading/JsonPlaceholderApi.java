package com.example.jsonreading;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceholderApi {

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer userId,
                              @Query("userId") Integer userId2,
                              @Query("_sort") String sort,
                              @Query("_order") String order);
}
