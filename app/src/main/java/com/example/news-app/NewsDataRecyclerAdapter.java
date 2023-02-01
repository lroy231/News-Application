package com.example.newmidtermmakeup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsDataRecyclerAdapter extends RecyclerView.Adapter<NewsDataRecyclerAdapter.NewsViewHolder> {
    ArrayList<News> newsArrayList;
    Context context;
    NewRecyclerListener listener;

    public NewsDataRecyclerAdapter(ArrayList<News> data, Context context, NewRecyclerListener listener){
        this.newsArrayList = data;
        this.context = context;
        this.listener = listener;

    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout2, parent, false);

        NewsViewHolder newsViewHolder = new NewsViewHolder(view, listener);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        holder.author.setText(news.getAuthor());
        holder.title.setText(news.getTitle());
        holder.description.setText(news.getDescription());
        holder.date.setText(news.getDate());
        holder.icon = news.getUrlLink();
        Picasso.with(context).load(holder.icon).into(holder.imageView);
        holder.position = position;
        holder.link = news.getUrl();
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView author;
        TextView title;
        TextView description;
        TextView date;
        ImageView imageView;
        String icon;
        int position;
        String link;
        public NewsViewHolder(@NonNull View itemView, final NewRecyclerListener listener) {
            super(itemView);
            author = itemView.findViewById(R.id.newsAuthor);
            title = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.newsDescrip);
            date = itemView.findViewById(R.id.newsDate);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: " + position);
                    listener.toBrowser(link);
                }
            });
        }
    }

    interface NewRecyclerListener{
        void toBrowser(String link);
    }
}
