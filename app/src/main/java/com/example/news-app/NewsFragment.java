package com.example.newmidtermmakeup;

import android.os.Bundle;

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
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    private final OkHttpClient client = new OkHttpClient();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    NewsDataRecyclerAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mTitle;
    private String mParam2;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        getActivity().setTitle(mTitle);
        showEverything(mTitle);
        recyclerView = view.findViewById(R.id.recyclerVIew);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        return view;
    }

    void showEverything(String name){
        String tName = name;
        Request request = new Request.Builder()
               .url("https://newsapi.org/v2/everything?q="+tName+"&apiKey=badb5141127f4011921cb485c7f3a6a2")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ArrayList<News> newsArrayList = new ArrayList<>();
                if(response.isSuccessful()){
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        JSONArray list = json.getJSONArray("articles");
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject jsonObject = list.getJSONObject(i);
                            News news = new News();
                            news.setAuthor(jsonObject.getString("author"));
                            news.setTitle(jsonObject.getString("title"));
                            news.setDescription(jsonObject.getString("description"));
                            news.setUrl(jsonObject.getString("url"));
                            news.setUrlLink(jsonObject.getString("urlToImage"));
                            news.setDate(jsonObject.getString("publishedAt"));
                            newsArrayList.add(news);
                        }

                        Log.d("TAG", "onResponse: " + newsArrayList.get(0).getAuthor());
                        Log.d("TAG", "onResponseTOO: " + newsArrayList.get(0).getDescription());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.setLayoutManager(layoutManager);
                                adapter = new NewsDataRecyclerAdapter(newsArrayList, getActivity(), (NewsDataRecyclerAdapter.NewRecyclerListener) getActivity());
                                recyclerView.setAdapter(adapter);
                                Log.d("AG", "onCreateView: " + newsArrayList.size());
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else{
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Log.d("TAG", "onResponse: " + body);
                }
            }
        });
    }

}