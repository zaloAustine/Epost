package com.example.mobidevtask.Utils;

import android.content.Context;
import android.widget.Toast;

import com.example.mobidevtask.Adapters.All_Post_Adapter;
import com.example.mobidevtask.Api.MyApi;
import com.example.mobidevtask.models.postModel.Message;
import com.example.mobidevtask.models.postModel.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util_Load_Post {

    public void loadPost(final Context context, final List<data> mydata,final All_Post_Adapter adapter){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-api.mobidev-sandbox.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        final Call<Message> message =  api.getAllPosts();

        message.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context,response.code()+"failed",Toast.LENGTH_LONG).show();
                    return;
                }

                Message m = response.body();

                List<data> data = m.getData();

                mydata.clear();

                mydata.addAll(data);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(context,"No internet",Toast.LENGTH_LONG).show();


            }
        });
    }}
