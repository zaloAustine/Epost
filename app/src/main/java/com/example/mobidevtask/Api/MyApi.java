package com.example.mobidevtask.Api;

import com.example.mobidevtask.models.commentModel.data2;
import com.example.mobidevtask.models.postModel.Message;
import com.example.mobidevtask.models.commentModel.comment;
import com.example.mobidevtask.models.postModel.data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface MyApi {

    @GET("posts")
    Call<Message> getAllPosts();

    @FormUrlEncoded
    @POST("posts")
   Call<data> createPost(@Field("title") String title,@Field("body") String body);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

    @DELETE("posts/{id}")
    Call<Void> deleteComments(@Path("id") int id);


    @GET("posts/comments/{id}")
    Call<comment> getAllComments(@Path("id") int id);

    @PATCH("posts/{id}")
    Call<data> editPost(@Path("id") int id, @Body data data);


    @PATCH("comments/like/{id}")
    Call<data2> likeComment(@Path("id") int id, @Body data2 data);


    @DELETE("comments/{id}")
    Call<Void> deleteComment(@Path("id") int id);

    @FormUrlEncoded
    @POST("comments/post/{id}")
    Call<data2> commentPost(@Path("id") int id , @Field("body") String body);

}
