package com.example.newmidtermmakeup;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import okhttp3.OkHttpClient;


public class NewSourceCategoryFragment extends Fragment {
    ListView listView;
    private final OkHttpClient client = new OkHttpClient();
    ArrayList<NewsData.Category> categoriesList = new ArrayList<>();
    ArrayAdapter<NewsData.Category> adapter;
    final String TAG = "demo";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_source_category, container, false);
        listView = view.findViewById(R.id.listView);
        getActivity().setTitle("News Source Categories");
        categoriesList.clear();
        categoriesList.addAll(NewsData.categories);
        adapter = new ArrayAdapter<NewsData.Category>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, categoriesList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "onItemClick: " + position);
                String selected_Category = categoriesList.get(position).getCategory();
                Log.d(TAG, "onItemClick: " + selected_Category);
                mListener.toNewSource(selected_Category);
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CategoryListener) context;
    }

    CategoryListener mListener;
    interface CategoryListener{
        void toNewSource(String category);
    }
}