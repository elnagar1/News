package com.example.news.ui.main;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.news.Adapters.HimitAdapter;
import com.example.news.Models.HimitModel;
import com.example.news.R;
import com.example.news.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.todkars.shimmer.ShimmerRecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HimitActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    private final HimitAdapter himitAdapter = new HimitAdapter();
    private ShimmerRecyclerView shimmerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_himit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        shimmerRecyclerView = findViewById(R.id.recycler);
        shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shimmerRecyclerView.setAdapter(himitAdapter);
        shimmerRecyclerView.showShimmer();



        databaseReference = FirebaseDatabase.getInstance().getReference("Database").child("Posts");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    HashMap<String, HimitModel> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, HimitModel>>() {
                    });
                    List<HimitModel> posts = new ArrayList<>(results.values());

                    himitAdapter.setList(posts);
                    himitAdapter.notifyDataSetChanged();
                    shimmerRecyclerView.hideShimmer();

                } else {
                    Toast.makeText(HimitActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    himitAdapter.notifyDataSetChanged();
                    shimmerRecyclerView.hideShimmer();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getParent(), "XX Error : post not add  ", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            CustomDialog();
        });
    }

    public void CustomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.diacard);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        Button cancel = dialog.findViewById(R.id.Cancel);
        Button save = dialog.findViewById(R.id.Save);
        EditText title = dialog.findViewById(R.id.title);
        EditText des = dialog.findViewById(R.id.Description);

        save.setOnClickListener(v -> {

            if (!Utils.validateForm(dialog)) {
                return;
            }
            String titl = title.getText().toString();
            String de = des.getText().toString();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm ");
            Date date = new Date();
            String dateStr = dateFormat.format(date);
            String id = databaseReference.push().getKey();
            Toast.makeText(this, "XX Successful : id creat  " + id, Toast.LENGTH_SHORT).show();
            databaseReference.child(id).setValue(new HimitModel(id, titl, de, dateStr, "Image Url", ""))
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "XX Successful : post add  " + id, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(this, "XX Error : post not add  ", Toast.LENGTH_SHORT).show();
                        }
                    });


        });


        cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }





}
