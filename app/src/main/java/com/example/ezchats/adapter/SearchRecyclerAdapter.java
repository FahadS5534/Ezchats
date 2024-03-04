package com.example.ezchats.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezchats.Chating;
import com.example.ezchats.R;
import com.example.ezchats.model.User;
import com.example.ezchats.util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.Objects;

public class SearchRecyclerAdapter extends FirestoreRecyclerAdapter<User,SearchRecyclerAdapter.UserViewHolder> {
   Context con;
    public SearchRecyclerAdapter(@NonNull FirestoreRecyclerOptions<User> options,Context con) {
        super(options);
        this.con=con;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
        if(Objects.equals(FirebaseUtil.userId(), model.getUserId())){
            holder.name.setText(model.getFirstName()+"(You)");
            holder.phone.setText("Message Yourself");
            holder.profilepic.setImageResource(R.drawable.img_2);
        }
        else {
            holder.name.setText(model.getFirstName());
            holder.phone.setText(model.getPhone());
            holder.profilepic.setImageResource(R.drawable.img_2);
        }
        holder.itemView.setOnClickListener(v->{
            Intent intent=new Intent(con,Chating.class);
            intent.putExtra("userName",model.getFirstName());


            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            con.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(con).inflate(R.layout.seacrh_recycler,parent,false);

        return new UserViewHolder(view);
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView phone;
        ImageView profilepic;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.firstNameR);
            phone=itemView.findViewById(R.id.phoneNumber);
            profilepic=itemView.findViewById(R.id.imageView3);
        }
    }
}
