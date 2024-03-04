package com.example.ezchats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {


    String phone;
    EditText otp;
    String verification;
    Long timeOut=60L;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    TextView resend;
    FirebaseAuth auth= FirebaseAuth.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        phone= Objects.requireNonNull(getIntent().getExtras()).getString("phone");
        otp=findViewById(R.id.otp);
        resend=findViewById(R.id.textView3);
        findViewById(R.id.button).setOnClickListener(v -> {
            String enter=otp.getText().toString();
            PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(verification,enter);
            signIn(phoneAuthCredential);
        });
        resend.setOnClickListener(v->{
           sendOtp(phone,true);
        });
        sendOtp(phone,false);

    }
    void sendOtp(String phone,boolean isResend){
        startTimer();
        PhoneAuthOptions.Builder builder=PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+91 "+phone)
                .setActivity(this)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signIn(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(otp.this, "Verification failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verification=s;
                        resendingToken=forceResendingToken;
                    }
                });
        if(isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        }
        else{
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }

    }

    private void startTimer() {
     resend.setEnabled(false);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                resend.setText(String.format("Resend Otp in %d Seconds", timeOut));
             timeOut--;
             if(timeOut<=0){
                 timeOut=60L;
                 timer.cancel();
                 runOnUiThread(
                         new Runnable() {
                             @Override
                             public void run() {
                                 resend.setEnabled(true);
                             }}
                 );

             }
            }
        },0,1000);

    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
              Intent intent=new Intent(otp.this,EnterName.class);
              intent.putExtra("phone1",phone);
              startActivity(intent);
                }
                else{
                    Toast.makeText(otp.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}