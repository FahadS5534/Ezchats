package com.example.ezchats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.ezchats.adapter.SearchRecyclerAdapter;
import com.example.ezchats.model.User;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SearchActivity extends AppCompatActivity {

    EditText editText;
    SearchRecyclerAdapter searchRecyclerAdapter;
    RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViewById(R.id.back).setOnClickListener(v -> onBackPressed());
        editText=findViewById(R.id.search);
        recyclerView=findViewById(R.id.recycler);
        editText.requestFocus();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               String searchTerm=s.toString();
               setUpRecycler(searchTerm);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void setUpRecycler(String searchTerm) {
        Query query= FirebaseFirestore.getInstance().collection("users")
                .whereLessThanOrEqualTo("firstName",searchTerm.toLowerCase());
        FirestoreRecyclerOptions<User> firestoreRecyclerOptions= new FirestoreRecyclerOptions.Builder<User>().setQuery(query,User.class).build();

        searchRecyclerAdapter=new SearchRecyclerAdapter(firestoreRecyclerOptions,getApplicationContext());
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setAdapter(searchRecyclerAdapter);
      searchRecyclerAdapter.startListening();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(searchRecyclerAdapter!=null){
            searchRecyclerAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(searchRecyclerAdapter!=null){
            searchRecyclerAdapter.stopListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(searchRecyclerAdapter!=null){
            searchRecyclerAdapter.stopListening();
        }
    }
}