package com.example.newmidtermmakeup;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewSourceRecyclerAdapter extends RecyclerView.Adapter<NewSourceRecyclerAdapter.NewSourceViewHolder> {
    ArrayList<Source> sourceArrayList;


    public NewSourceRecyclerAdapter(ArrayList<Source> data, DataRecyclerListener recyclerListener){
        this.sourceArrayList = data;
        this.recyclerListener = recyclerListener;

    }

    @NonNull
    @Override
    public NewSourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, parent, false);

        NewSourceViewHolder newSourceViewHolder = new NewSourceViewHolder(view, recyclerListener);
        return newSourceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewSourceViewHolder holder, int position) {
        Source source = sourceArrayList.get(position);
        holder.title.setText(source.getTitle());
        holder.description.setText(source.getDescription());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return sourceArrayList.size();
    }

    public static class NewSourceViewHolder extends  RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        int position;

        public NewSourceViewHolder(@NonNull View itemView, final DataRecyclerListener recyclerListener) {
            super(itemView);
            title = itemView.findViewById(R.id.catagory);
            description = itemView.findViewById(R.id.body);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: " + position);
                    recyclerListener.toNewsFragment(title.getText().toString());
                }
            });
        }
    }
    DataRecyclerListener recyclerListener;
    interface DataRecyclerListener{
        void toNewsFragment(String title);
    }
}
