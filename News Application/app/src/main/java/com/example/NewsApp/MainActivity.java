package com.example.midtermmakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements NewSourceCategoryFragment.CategoryListener {
    private final OkHttpClient client = new OkHttpClient();
    final String TAG = "demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerView, new NewSourceCategoryFragment())
                .commit();

    }
    String mCategory;
    @Override
    public void toNewSource(String category) {
        this.mCategory = category;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, NewsSourceFragment.newInstance(mCategory))
                .commit();
    }
}