package com.example.mobidevtask.Utils;

import android.content.Context;
import android.widget.Toast;

import com.example.mobidevtask.Adapters.Comment_Adapter;
import com.example.mobidevtask.Api.MyApi;
import com.example.mobidevtask.models.commentModel.comment;
import com.example.mobidevtask.models.commentModel.data2;
import com.example.mobidevtask.ui.ViewPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util_load_Comments {

    public  void LoadComments(final Context context, int id2, final List<data2> commentList, final Comment_Adapter adapter){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-api.mobidev-sandbox.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        final Call<comment> commentCall = api.getAllComments(id2);
        commentCall.enqueue(new Callback<comment>() {
            @Override
            public void onResponse(Call<comment> call, Response<comment> response) {

                comment d = response.body();

                List<data2> data = d.getData();

                    commentList.clear();

                    commentList.addAll(data);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<comment> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });


    }

}
