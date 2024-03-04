package com.example.ezchats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ezchats.model.User;
import com.example.ezchats.util.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class EnterName extends AppCompatActivity {
     EditText firstName;
     Button save;
     String phone;
     User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entername);
        firstName=findViewById(R.id.firstname);
        save=findViewById(R.id.savebutton);
        save.setOnClickListener(v -> {
          setUserName();
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("phone1")) {
            phone = extras.getString("phone1");
            getUsername();
        } else {

            Toast.makeText(this, "Phone number not provided", Toast.LENGTH_SHORT).show();

        }
        getUsername();

    }

    private void setUserName() {
        if(firstName.getText().toString().isEmpty()){
            firstName.setError("First Name is Required");
            return;
        }
        if(user!=null){
            user.setFirstName(firstName.getText().toString());
        }
        else{
            user=new User(phone,firstName.getText().toString(), Timestamp.now(),FirebaseUtil.userId());
        }
        FirebaseUtil.currentUserDetails().set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
//                Intent intent=new Intent(EnterName.this,ChatScreen.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
                    startActivity(new Intent(EnterName.this,ChatScreen.class));

                }
                else{
                    Toast.makeText(EnterName.this, "Unable to save details:<", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getUsername() {
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                   user= task.getResult().toObject(User.class);
                   if(user!=null){
                       firstName.setText(user.getFirstName());

                   }
                }
            }
        });
    }
}