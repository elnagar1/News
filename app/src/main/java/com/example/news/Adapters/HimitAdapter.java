package com.example.news.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.news.Models.HimitModel;
import com.example.news.Models.UserModel;
import com.example.news.R;
import com.example.news.ui.main.DetailActivity;
import com.example.news.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HimitAdapter extends RecyclerView.Adapter<HimitAdapter.NewsViewHolder> {

    DatabaseReference databaseReference;
    List<HimitModel> himitModelList = new ArrayList<>();
    FirebaseAuth mAuth;
    FirebaseUser user;

    private Context context;


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.himitcard, parent, false);
        return new NewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        HimitModel himitModel = himitModelList.get(position);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        holder.title.setText(himitModel.getTitle());
        holder.time.setText(himitModel.getPublishedAt().replace("T", "  "));
        holder.details.setText(himitModel.getDescription());


        if (user != null) {

            String s = user.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference("users").child(s);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    UserModel users = dataSnapshot.getValue(UserModel.class);
                    if (dataSnapshot.exists()) {
                        holder.name.setText(users.getName());
                        Log.e("xx", "onDataChange: " + users.getPhoto());


                        Glide.with(context.getApplicationContext())
                                .load(users.getPhoto())
                                .apply(RequestOptions.circleCropTransform())
                                .into(holder.userIcon);
                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("xxx", "onBindViewHolder: " + databaseError.getMessage());
                }
            });
        }

        Glide.with(context).load(himitModel.getUrlToImage()).into(holder.newsImg);
        holder.overflow.setOnClickListener(view ->
                showPopupMenu(holder.overflow, position));


    }


    @Override
    public int getItemCount() {
        return himitModelList.size();
    }


    public void setList(List<HimitModel> articles) {
        this.himitModelList.clear();
        this.himitModelList = articles;
        notifyDataSetChanged();
        Log.e(this.getClass().getName(), " ArrayList Add Successfully");
    }

    private void showPopupMenu(View view, int position) {

        if (Utils.checkUser(context)) {
        HimitModel himitModel = himitModelList.get(position);

        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.delete:
                        delete(himitModel, position);
                        return true;
                    case R.id.update:
                        update(himitModel, position);
                        return true;
                    default:
                }
                return false;
            });
            popup.show();
        }
    }

    //UpdatePost
    private void update(HimitModel himitModel, int position) {


        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.diacard);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        Button cancel = dialog.findViewById(R.id.Cancel);
        Button save = dialog.findViewById(R.id.Save);

        EditText title = dialog.findViewById(R.id.title);
        EditText des = dialog.findViewById(R.id.Description);

        title.setText(himitModel.getTitle());
        des.setText(himitModel.getDescription());

        save.setOnClickListener(v -> {
            if (!Utils.validateForm(dialog)) {
                return;
            }
            String titl = title.getText().toString();
            String de = des.getText().toString();
            String id = himitModel.getId();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm ");
            Date date = new Date();
            String dateStr = dateFormat.format(date);


            databaseReference = FirebaseDatabase.getInstance().getReference("Database").child("Posts").child(himitModel.getId());
            databaseReference.setValue(new HimitModel(id, titl, de, dateStr, "Image Url", "")).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    Toast.makeText(context, R.string.Updated, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    notifyItemRemoved(position);
                } else {
                    Toast.makeText(context, R.string.NUpdate, Toast.LENGTH_SHORT).show();

                }
            });

        });


        cancel.setOnClickListener(v -> {
            dialog.dismiss();

        });
        dialog.show();


    }

    //DeletePost
    private void delete(HimitModel himitModel, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Database").child("Posts").child(himitModel.getId());
        databaseReference.removeValue((databaseError, databaseReference) -> {

            Toast.makeText(context, R.string.Deleted, Toast.LENGTH_SHORT).show();
            notifyItemRemoved(position);
        });

    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView time, title, details, name;
        ImageView newsImg, overflow, userIcon;

        private NewsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            time = view.findViewById(R.id.news_time);
            title = view.findViewById(R.id.news_title);
            details = view.findViewById(R.id.news_details);
            newsImg = view.findViewById(R.id.image);
            overflow = view.findViewById(R.id.overflow);
            userIcon = view.findViewById(R.id.userIcon);

            view.findViewById(R.id.ripple).setOnClickListener(v -> {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Utils.Json_title, himitModelList.get(getAdapterPosition()).getTitle());
                intent.putExtra(Utils.Json_fullNewUrl, himitModelList.get(getAdapterPosition()).getUrl());
                context.startActivity(intent);
            });


        }


    }


}
