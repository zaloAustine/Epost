package com.example.mobidevtask.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.mobidevtask.Adapters.Comment_Adapter;
import com.example.mobidevtask.Utils.Util_Delete_Post;
import com.example.mobidevtask.Utils.Util_Edit_Post;
import com.example.mobidevtask.Utils.Util_Post_Comment;
import com.example.mobidevtask.Utils.Util_load_Comments;
import com.example.mobidevtask.models.commentModel.data2;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.mobidevtask.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPost extends AppCompatActivity implements View.OnClickListener {

    TextView title,body;
    CardView delete,edit;
    String Ibody,Ititle;
    int id;
    List<data2> commentsList;
    RecyclerView comments;
    Comment_Adapter adapter;
    EditText commenttext;
    ImageView post;
    Util_load_Comments load_comments;

     SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        Intent i = getIntent();
        Ititle =  i.getStringExtra("title");
        Ibody =  i.getStringExtra("body");
        id = i.getIntExtra("id",0);

        title = findViewById(R.id.title2);
        title.setText(Ititle);
        body = findViewById(R.id.body2);
        body.setText(Ibody);


        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(this);


        setRecyclerView();
        load_comments= new Util_load_Comments();
        load_comments.LoadComments(ViewPost.this,id,commentsList,adapter);

        commenttext = findViewById(R.id.postComement);
        post = findViewById(R.id.postC);
        post.setOnClickListener(this);

        adapter.notifyDataSetChanged();


        swipeRefresh = findViewById(R.id.swipeRefresh2);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                load_comments.LoadComments(ViewPost.this,id,commentsList,adapter);
                if(swipeRefresh.isRefreshing()){
                    swipeRefresh.setRefreshing(false);
                    TextView text = findViewById(R.id.swipetext2);
                    text.setVisibility(View.GONE);
                }



            }
        });

    }

    private void ShowDialog() {

            final AlertDialog BalertDialog;
            final TextView text_to_delete;


            LayoutInflater inflater = LayoutInflater.from(this);
            final View itemView = inflater.inflate(R.layout.delete_dialog, null);

            text_to_delete = itemView.findViewById(R.id.deleteText);
            text_to_delete.setText(Ititle+"\n"+Ibody);


            BalertDialog = new AlertDialog.Builder(this).setView(itemView).create();
            BalertDialog.setButton(AlertDialog.BUTTON1, "delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Util_Delete_Post delete_post = new Util_Delete_Post();
                    delete_post.Delete(id,ViewPost.this);

                    BalertDialog.dismiss();
                }
            });
        BalertDialog.setButton(AlertDialog.BUTTON2, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BalertDialog.dismiss();
            }
        });
            BalertDialog.show();



    }




public void setRecyclerView(){
    commentsList = new ArrayList<>();
    comments = findViewById(R.id.comments);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    final StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
    gridLayoutManager.setReverseLayout(true);
    comments.setLayoutManager(gridLayoutManager);

    comments.setNestedScrollingEnabled(false);
    comments.setHasFixedSize(true);

    adapter = new Comment_Adapter(ViewPost.this,commentsList);
    comments.setAdapter(adapter);
    adapter.notifyDataSetChanged();
}



public void EditDialog(final int id){

    final AlertDialog BalertDialog;
    final EditText title,body;


    LayoutInflater inflater = LayoutInflater.from(this);
    final View itemView = inflater.inflate(R.layout.edit_dialog, null);

    title = itemView.findViewById(R.id.titleE);
    body = itemView.findViewById(R.id.bodyE);
    title.setText(Ititle);
    body.setText(Ibody);



    BalertDialog = new AlertDialog.Builder(this).setView(itemView).create();
    BalertDialog.setButton(AlertDialog.BUTTON1, "Edit", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            Util_Edit_Post edit_post = new Util_Edit_Post();
            edit_post.Edit(title.getText().toString(),body.getText().toString(),ViewPost.this,id);



            BalertDialog.dismiss();
        }
    });
    BalertDialog.setButton(AlertDialog.BUTTON2, "cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            BalertDialog.dismiss();
        }
    });
    BalertDialog.show();



}


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.delete:
                ShowDialog();
                adapter.notifyDataSetChanged();
                break;
            case R.id.postC:
                Util_Post_Comment post_comment = new Util_Post_Comment();
                String comment = commenttext.getText().toString();
                if(comment.isEmpty()){
                        commenttext.setError("Enter Comment");
                }else{
                    post_comment.PostComment(id,comment,ViewPost.this,v);
                    swipeRefresh.setRefreshing(true);
                    load_comments.LoadComments(ViewPost.this,id,commentsList,adapter);
                    swipeRefresh.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                    commenttext.setText("");

                }
                break;
            case R.id.edit:
                EditDialog(id);
                break;



        }
    }
}
