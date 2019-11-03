package com.example.mobidevtask.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobidevtask.Api.MyApi;
import com.example.mobidevtask.R;
import com.example.mobidevtask.ui.ViewPost;

import androidx.appcompat.app.AlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util_Delete_Post {

    public void Delete(int id,final Context context){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-api.mobidev-sandbox.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        final Call<Void> message =  api.deletePost(id);
        message.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

        Toast.makeText(context,"post delete",Toast.LENGTH_LONG).show();


    }



}
