package com.example.ezchats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class Chating extends AppCompatActivity {

    TextView userName;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);
        userName=findViewById(R.id.textView6);
        userName.setText(Objects.requireNonNull(getIntent().getExtras()).getString("userName"));
        findViewById(R.id.back).setOnClickListener(v -> onBackPressed());

    }
}