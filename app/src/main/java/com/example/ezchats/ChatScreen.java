package com.example.ezchats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChatScreen extends AppCompatActivity {

  BottomNavigationView bottomNavigationView;
  ImageButton search;
  ChatFragment chats;
  ProfileFragment profiles;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        bottomNavigationView=findViewById(R.id.bottomNav);
        chats=new ChatFragment();
        profiles=new ProfileFragment();
        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId()==R.id.chat){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,chats).commit();

            }
            if(item.getItemId()==R.id.Profile){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,profiles).commit();

            }

       return true;
        });
        bottomNavigationView.setSelectedItemId(R.id.chat);
        findViewById(R.id.searchBtn).setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
    }
}