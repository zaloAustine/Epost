package com.example.mobidevtask.Adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobidevtask.R;
import com.example.mobidevtask.Utils.Util_Delete_Post;
import com.example.mobidevtask.Utils.Util_Edit_Post;
import com.example.mobidevtask.models.postModel.data;
import com.example.mobidevtask.ui.ViewPost;

import java.util.List;

import javax.xml.datatype.Duration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class All_Post_Adapter extends RecyclerView.Adapter<All_Post_Adapter.ViewHolder> {

    Context mcontext;
    List<data> item;
    data k, b;
    public boolean on_attach = true;

    public All_Post_Adapter(Context mcontext, List<data> item) {
        this.mcontext = mcontext;
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.post_item, parent, false);

        return new ViewHolder(v);
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        k = item.get(position);
        holder.title.setText(k.getTitle());
        holder.body.setText(k.getBody());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k = item.get(position);
                Intent i = new Intent(mcontext, ViewPost.class);
                i.putExtra("id", k.getId());
                i.putExtra("title", k.getTitle());
                i.putExtra("body", k.getBody());
                mcontext.startActivity(i);

            }
        });
        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k = item.get(position);
                Intent i = new Intent(mcontext, ViewPost.class);
                i.putExtra("id", k.getId());
                i.putExtra("title", k.getTitle());
                i.putExtra("body", k.getBody());
                mcontext.startActivity(i);

            }
        });

        holder.cardView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuItem edit = menu.add(Menu.NONE, 2, 2, "Edit");
                MenuItem edit2 = menu.add(Menu.NONE, 2, 2, "Delete");


                edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem items) {
                        switch (items.getItemId()) {
                            case 2:
                                b = item.get(position);
                                EditDialog(b.getId(), holder.title.getText().toString(), holder.body.getText().toString());
                                break;
                        }
                        return false;
                    }
                });
                edit2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem items) {
                        switch (items.getItemId()) {
                            case 2:
                                b = item.get(position);
                                Util_Delete_Post delete_post = new Util_Delete_Post();
                                delete_post.Delete(b.getId(), mcontext);
                                break;

                        }
                        return false;
                    }
                });
            }
        });


        holder.createdat.setText(k.getCreated_at());

       // setAnimation(holder.itemView, position);
        RightLeft(holder.itemView,position);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title, body, createdat;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            createdat = itemView.findViewById(R.id.createdat);


        }
    }

    public void EditDialog(final int id, String Ititle, String Ibody) {

        final AlertDialog BalertDialog;
        final EditText title, body;


        LayoutInflater inflater = LayoutInflater.from(mcontext);
        final View itemView = inflater.inflate(R.layout.edit_dialog, null);

        title = itemView.findViewById(R.id.titleE);
        body = itemView.findViewById(R.id.bodyE);
        title.setText(Ititle);
        body.setText(Ibody);


        BalertDialog = new AlertDialog.Builder(mcontext).setView(itemView).create();
        BalertDialog.setButton(AlertDialog.BUTTON1, "Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Util_Edit_Post edit_post = new Util_Edit_Post();
                edit_post.Edit(title.getText().toString(), body.getText().toString(), mcontext, id);


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


    public void setAnimation(View itemview, int i) {

        if (!on_attach) {
            i = -1;
        }


        boolean isNotFirst = i == -1;
        i++;

        itemview.setAlpha(0.f);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemview, "alpha", 0.f, 0.5f, 1.0f);
        animator.setStartDelay(isNotFirst ? 500 / 2 : (i * 500 / 3));
        animator.setDuration(500);
        set.play(animator);
        animator.start();


    }


    public void RightLeft(View itemview, int i) {
        if (!on_attach) {
            i = -1;
        }


        boolean isNotFirst = i == -1;
        i = i + 1;
        itemview.setTranslationX(itemview.getX() + 400);
        itemview.setAlpha(0.f);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(itemview, "translationX", itemview.getX(), +400,0);
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(itemview, "alpha", 1.f);
        ObjectAnimator.ofFloat(itemview, "alpha", 0.f).start();
        animatorY.setStartDelay(isNotFirst ? 150 : (i * 150));
        animatorY.setDuration((isNotFirst ? 2 : 1) * 150);
        set.playTogether(animatorY, animatorx);
        set.start();

    }
}



