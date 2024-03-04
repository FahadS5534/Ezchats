package com.example.ezchats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ezchats.util.FirebaseUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v ->{
            if(FirebaseUtil.isLoggedIn()){
                startActivity(new Intent(MainActivity.this,ChatScreen.class));
            }
            else{
                startActivity(new Intent(this, signup.class));
            }

        });
    }
}