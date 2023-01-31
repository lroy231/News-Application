package com.example.midtermmakeup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewSourceRecyclerAdapter extends RecyclerView.Adapter<NewSourceRecyclerAdapter.NewSourceViewHolder> {
    ArrayList<Source> sourceArrayList;
    Context context;

    public NewSourceRecyclerAdapter(ArrayList<Source> data, Context context){
        this.sourceArrayList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public NewSourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, parent, false);

        NewSourceViewHolder newSourceViewHolder = new NewSourceViewHolder(view);
        return newSourceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewSourceViewHolder holder, int position) {
        Source source = sourceArrayList.get(position);
        holder.title.setText(source.getTitle());
        holder.description.setText(source.getDescription());

    }

    @Override
    public int getItemCount() {
        return sourceArrayList.size();
    }

    public static class NewSourceViewHolder extends  RecyclerView.ViewHolder{
        TextView title;
        TextView description;

        public NewSourceViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.catagory);
            description = itemView.findViewById(R.id.body);
        }
    }

}
