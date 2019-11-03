package com.example.mobidevtask.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mobidevtask.R;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        logoLauncher launcher = new logoLauncher();
        launcher.start();
    }


    class logoLauncher extends Thread{

        @Override
        public void run() {
            try{
                sleep(2000);

            }
            catch (Exception e){
                e.printStackTrace();
            }

            Intent main = new Intent(Launcher.this, MainActivity.class);
            startActivity(main);
            Launcher.this.finish();



        }
    }

}
