package com.example.mobidevtask.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mobidevtask.Adapters.FragmentAdater;
import com.example.mobidevtask.Api.MyApi;
import com.example.mobidevtask.R;
import com.example.mobidevtask.models.postModel.Message;
import com.example.mobidevtask.models.postModel.data;
import com.example.mobidevtask.ui.Fragments.AddPost;
import com.example.mobidevtask.ui.Fragments.AllPosts;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    BottomNavigationView bottomNavigationView2;
    MenuItem prevmenu;
    FragmentAdater fragmentAdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(this);
        setViewPager(viewPager);

        bottomNavigationView2 = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView2.setOnNavigationItemSelectedListener(this);


    }


    public void setViewPager(ViewPager viewPager){
         fragmentAdater = new FragmentAdater(getSupportFragmentManager());
       fragmentAdater.AddFragment(new AllPosts());
        fragmentAdater.AddFragment(new AddPost());
        viewPager.setAdapter(fragmentAdater);



    }

    public void net(){

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
                    Toast.makeText(MainActivity.this,response.code()+"call",Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();

                // TextView textView = findViewById(R.id.text);
                Message m = response.body();




                List<data> d = m.getData();
                for (data dt:d){
                    String content =dt.getTitle()+"\n"+dt.getBody()+"\n"+dt.getCreated_at()+"\n"+dt.getCreated_at()+"\n";
                    //    textView.append(content);

                }


            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(prevmenu!=null){
            prevmenu.setChecked(false);

        }else {
            bottomNavigationView2.getMenu().getItem(position).setChecked(false);
        }
        bottomNavigationView2.getMenu().getItem(position).setChecked(true);
        bottomNavigationView2.getMenu().getItem(position);

        fragmentAdater.getItem(position).isVisible();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setMessage("Are you sure you want to exit?");
        b.setTitle("Epost");

        b.setIcon(R.drawable.send);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                MainActivity.this.finish();
            }
        });
        b.setNegativeButton("No",null);
        b.create();
        b.show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.posts:
                viewPager.setCurrentItem(0);
                break;
            case R.id.Add:
                viewPager.setCurrentItem(1);
                break;
        }
        return false;
    }
}
