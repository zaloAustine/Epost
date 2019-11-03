package com.example.mobidevtask.Utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.mobidevtask.Api.MyApi;
import com.example.mobidevtask.models.commentModel.data2;
import com.example.mobidevtask.ui.ViewPost;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util_Post_Comment {

    public  void PostComment(int id, String comment, final Context context,final View v){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-api.mobidev-sandbox.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        Call<data2> data2Call = api.commentPost(id,comment);

        data2Call.enqueue(new Callback<data2>() {
            @Override
            public void onResponse(Call<data2> call, Response<data2> response) {
                Toast.makeText(context,String.valueOf(response.code()),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<data2> call, Throwable t) {

                Snackbar.make(v, "Comment Posted  Swipe to refresh", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



            }
        });



    }

}
