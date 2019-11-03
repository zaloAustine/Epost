package com.example.mobidevtask.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.mobidevtask.Adapters.All_Post_Adapter;
import com.example.mobidevtask.Api.MyApi;
import com.example.mobidevtask.models.postModel.Message;
import com.example.mobidevtask.models.postModel.data;

import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Load_Post_Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    public void net(final Context thiscontext, final List<data> mydata,final All_Post_Adapter adapter){

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
                    Toast.makeText(thiscontext,response.code()+"failed",Toast.LENGTH_LONG).show();
                    return;
                }

                Message m = response.body();

                List<data> data = m.getData();

                for(int i =0;i<data.size();i++){
                    mydata.add(data.get(i));

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(thiscontext,"No Internet",Toast.LENGTH_LONG).show();

            }
        });
    }

}
