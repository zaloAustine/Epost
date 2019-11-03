package com.example.mobidevtask.ui.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobidevtask.Api.MyApi;
import com.example.mobidevtask.R;
import com.example.mobidevtask.models.postModel.data;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPost extends Fragment {
    View v;
    Context thiscontext;
    EditText body,title;
    MaterialButton post;

    ProgressDialog progressDialog;


    public AddPost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        thiscontext = container.getContext();
        v =  inflater.inflate(R.layout.fragment_add_post, container, false);

        body = v.findViewById(R.id.body);
        title = v.findViewById(R.id.title);
        post = v.findViewById(R.id.post);

        progressDialog = new ProgressDialog(thiscontext);
        progressDialog.setTitle("Posting...");
        progressDialog.setCancelable(false);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mtitile = title.getText().toString();
                String mbody = body.getText().toString();

                if(mtitile.isEmpty()){
                    title.setError("Title id Required");
                }else if(mbody.isEmpty()){
                    body.setError("Body text is Required");
                }
                else{
                    post(mtitile,mbody);

                }
            }
        });


        return v;
    }

    private  void post(final String ptitle, String pbody){
        progressDialog.show();
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-api.mobidev-sandbox.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);
        final Call<data> message =  api.createPost(ptitle,pbody);

        message.enqueue(new Callback<data>() {
            @Override
            public void onResponse(Call<data> call, Response<data> response) {

                if(response.code()==200){
                    progressDialog.hide();
                    Toast.makeText(thiscontext,String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                    body.setText("");
                    title.setText("");


                }else
                {
                    Toast.makeText(thiscontext,"Connect to internet",Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<data> call, Throwable t) {
                progressDialog.hide();
                Snackbar.make(v, "Posted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                body.setText("");
                title.setText("");


            }
        });




    }

}
