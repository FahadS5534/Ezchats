package com.example.ezchats.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {
    public static String userId(){
        return FirebaseAuth.getInstance().getUid();
    }
    public static DocumentReference currentUserDetails(){
  return FirebaseFirestore.getInstance().collection("users").document(userId());
    }
    public static boolean isLoggedIn(){
        return userId() != null;
    }
}
