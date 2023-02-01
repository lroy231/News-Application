package com.example.newmidtermmakeup;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsSourceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsSourceFragment extends Fragment {
    private final OkHttpClient client = new OkHttpClient();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Source> sourceArrayList = new ArrayList<>();
    NewSourceRecyclerAdapter adapter;

    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mCategory;


    public NewsSourceFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NewsSourceFragment newInstance(String mCategory) {
        NewsSourceFragment fragment = new NewsSourceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_source, container, false);
        getActivity().setTitle(mCategory);
        showCategories(mCategory);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());

        return view;
    }

    void showCategories(String category){
        String aCategory = category;
        Request request = new Request.Builder()
                .url("https://newsapi.org/v2/sources?apiKey=badb5141127f4011921cb485c7f3a6a2&category="+aCategory+"")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ArrayList<Source> categories = new ArrayList<>();
                if(response.isSuccessful()) {
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        JSONArray list = json.getJSONArray("sources");
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject jsonObject = list.getJSONObject(i);
                            Source source1 = new Source();
                            source1.setTitle(jsonObject.getString("name"));
                            source1.setDescription(jsonObject.getString("description"));
                            categories.add(source1);
                        }

                        Log.d("TAG", "onResponse: " + categories.get(0).getTitle());
                        Log.d("TAG", "onResponseTOO: " + categories.get(0).getDescription());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.setLayoutManager(layoutManager);
                                adapter = new NewSourceRecyclerAdapter(categories, (NewSourceRecyclerAdapter.DataRecyclerListener) getActivity());
                                recyclerView.setAdapter(adapter);
                                Log.d("AG", "onCreateView: " + categories.size());

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Log.d("TAG", "onResponse: " + body);
                }
            }
        });
    }

}