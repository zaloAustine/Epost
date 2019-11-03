package com.example.mobidevtask.ui.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mobidevtask.Adapters.All_Post_Adapter;
import com.example.mobidevtask.R;
import com.example.mobidevtask.Utils.Util_Load_Post;

import com.example.mobidevtask.models.postModel.data;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllPosts extends Fragment {
   private View v;
    private Context thiscontext;
    private RecyclerView post_Recycler_View;
    private List<data> mydata;
    private All_Post_Adapter adapter;


    public AllPosts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_all_posts, container, false);
        thiscontext = container.getContext();



        post_Recycler_View = v.findViewById(R.id.post_Recycler_View);


        mydata = new ArrayList<>();

       final  Util_Load_Post Load_post = new Util_Load_Post();




        post_Recycler_View = v.findViewById(R.id.post_Recycler_View);


        final StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1,LinearLayoutManager.VERTICAL);
        gridLayoutManager.setReverseLayout(true);
        post_Recycler_View.setLayoutManager(gridLayoutManager);

        post_Recycler_View.setNestedScrollingEnabled(false);
        post_Recycler_View.setHasFixedSize(true);

        adapter = new All_Post_Adapter(thiscontext,mydata);
        post_Recycler_View.setAdapter(adapter);



        Load_post.loadPost(thiscontext,mydata,adapter);


        adapter.notifyDataSetChanged();
        post_Recycler_View.scheduleLayoutAnimation();


        final SwipeRefreshLayout swipeRefresh =v.findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Load_post.loadPost(thiscontext,mydata,adapter);

                if(swipeRefresh.isRefreshing()){

                    swipeRefresh.setRefreshing(false);
                    TextView text = v.findViewById(R.id.swipetext);
                    text.setVisibility(View.GONE);
                }



            }
        });

        return v;
    }




}
