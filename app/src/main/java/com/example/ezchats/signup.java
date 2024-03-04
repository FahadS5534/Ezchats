package com.example.ezchats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

     EditText phone;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        phone=findViewById(R.id.phone
        );
        findViewById(R.id.button).setOnClickListener(v -> {
            if(phone.getText().toString().length()<10){
               phone.setError("Enter a valid phone number");
            }
            else {
                Intent intent = new Intent(this, otp.class);
                intent.putExtra("phone", phone.getText().toString());

                startActivity(new Intent(intent));

            }});

    }
}