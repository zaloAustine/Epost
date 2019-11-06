package com.example.mobidevtask.Adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobidevtask.Api.MyApi;
import com.example.mobidevtask.R;
import com.example.mobidevtask.models.commentModel.data2;
import com.example.mobidevtask.models.postModel.data;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comment_Adapter extends RecyclerView.Adapter<Comment_Adapter.Comment_View_Holder> {

    Context thiscontext;
    data2 k,c;

    public Comment_Adapter(Context thiscontext, List<data2> commentsList) {
        this.thiscontext = thiscontext;
        this.commentsList = commentsList;
    }

    List<data2> commentsList;
    public boolean on_attach = true;




    @NonNull
    @Override
    public Comment_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(thiscontext).inflate(R.layout.comment_item,parent,false);

        return new Comment_View_Holder(v);
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);

    }


    @Override
    public void onBindViewHolder(@NonNull final Comment_View_Holder holder, final int position) {
        k = commentsList.get(position);
        holder.commentText.setText(k.getBody());
        holder.like.setText("Likes " + k.getLike());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = commentsList.get(position);
                DeleteComment(c.getId());

            }
        });

        holder.like_button.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                if(likeButton.isLiked()){

                    c = commentsList.get(position);
                    Like(c.getId());

                }else if(!likeButton.isLiked()){
                        holder.like.setText("0");
                }
            }


            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });

        holder.delete.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

                if(likeButton.isLiked()){
                c = commentsList.get(position);
                DeleteComment(c.getId());

            }

            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });

        setAnimation(holder.itemView,position);

    }


    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public class Comment_View_Holder extends RecyclerView.ViewHolder{

        TextView commentText,like;
        LikeButton delete;
        LikeButton like_button;

        public Comment_View_Holder(@NonNull View itemView) {
            super(itemView);

            commentText = itemView.findViewById(R.id.commentText);
            like = itemView.findViewById(R.id.likeComment);
            delete = itemView.findViewById(R.id.delete);
            like_button = itemView.findViewById(R.id.like_button);


        }
    }


    public void Like(int id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-api.mobidev-sandbox.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        data2 data  = new data2(1);


        Call<data2> postCall = api.likeComment(id,data);

        postCall.enqueue(new Callback<data2>() {
            @Override
            public void onResponse(Call<data2> call, Response<data2> response) {
                Toast.makeText(thiscontext,String.valueOf(response.code()),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<data2> call, Throwable t) {
                Toast.makeText(thiscontext,"comment liked",Toast.LENGTH_SHORT).show();

                Toast.makeText(thiscontext,"swipe to refresh",Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void DeleteComment(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rest-api.mobidev-sandbox.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        final Call<Void> message =  api.deleteComment(id);
        message.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Toast.makeText(thiscontext,"Comment Deleted",Toast.LENGTH_SHORT).show();

                Toast.makeText(thiscontext,"Swipe To refresh",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(thiscontext,t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });
    }

    public void setAnimation(View itemview, int i) {

        if (!on_attach) {
            i = -1;
        }


        boolean isNotFirst = i == -1;
        i++;

        itemview.setAlpha(0.f);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemview, "alpha", 0.f, 0.5f, 1.0f);
        animator.setStartDelay(isNotFirst ? 500 / 2 : (i * 400 / 3));
        animator.setDuration(400);
        set.play(animator);
        animator.start();


    }


}
