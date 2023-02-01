package com.example.newmidtermmakeup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewCategoryAdapter extends RecyclerView.Adapter<NewCategoryAdapter.CategoryViewHolder> {
    ArrayList<NewsData> newsDataArrayList;
    public NewCategoryAdapter(ArrayList<NewsData> data){
        this.newsDataArrayList = data;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
    NewsData data = newsDataArrayList.get(position);
    holder.cat.setText(data.toString());

    }


    @Override
    public int getItemCount() {
        return newsDataArrayList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView cat;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cat = itemView.findViewById(R.id.categoryView);
        }
    }

}
