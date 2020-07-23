package com.example.news.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.Models.SeModel;
import com.example.news.R;
import com.example.news.ui.main.HimitActivity;
import com.example.news.ui.main.NewsActivity;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ma> {

    List<SeModel> list;
    Context context;

    public MainAdapter(List<SeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ma onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sectioncard, parent, false);
        return new ma(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ma holder, int position) {
        SeModel seModel = list.get(position);
        holder.title.setText(seModel.getCategory());
        holder.count.setText(seModel.getName());

        // loading album cover using Glide library
        Glide.with(context).load(seModel.getLLink()).into(holder.thumbnail);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }




public class ma extends RecyclerView.ViewHolder {

    public TextView title, count;
    public ImageView thumbnail, overflow;

    public ma(View view) {
        super(view);

        title = view.findViewById(R.id.title);
        count = view.findViewById(R.id.count);
        thumbnail = view.findViewById(R.id.thumbnail);


        view.findViewById(R.id.ripple).setOnClickListener(v -> {
            if (list.get(getAdapterPosition()).getCategory() == "HIMIT") {
                Intent intent = new Intent(context, HimitActivity.class);
                intent.putExtra("category", list.get(getAdapterPosition()).getCategory());
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra("category", list.get(getAdapterPosition()).getCategory());
                context.startActivity(intent);
            }
        });


    }

}


}
