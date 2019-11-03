package com.example.mobidevtask.Utils;

import android.content.Context;
import android.widget.Toast;

import com.example.mobidevtask.Api.MyApi;
import com.example.mobidevtask.models.postModel.data;
import com.example.mobidevtask.ui.ViewPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util_Edit_Post {


    public void Edit(final String Title, String body, final Context context, int id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-api.mobidev-sandbox.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        data data  = new data(Title,body);


        Call<data> postCall = api.editPost(id,data);

        postCall.enqueue(new Callback<com.example.mobidevtask.models.postModel.data>() {
            @Override
            public void onResponse(Call<com.example.mobidevtask.models.postModel.data> call, Response<com.example.mobidevtask.models.postModel.data> response) {
                if(response.code()==200){
                    Toast.makeText(context,"Post Updated",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.mobidevtask.models.postModel.data> call, Throwable t) {
                Toast.makeText(context,"Post Updated",Toast.LENGTH_LONG).show();
                Toast.makeText(context,"Swipe to refresh",Toast.LENGTH_SHORT).show();


            }
        });


    }

}
