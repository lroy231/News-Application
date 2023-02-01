package com.example.newmidtermmakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements NewSourceCategoryFragment.CategoryListener, NewSourceRecyclerAdapter.DataRecyclerListener, NewsDataRecyclerAdapter.NewRecyclerListener {
    private final OkHttpClient client = new OkHttpClient();
    final String TAG = "demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerView, new NewSourceCategoryFragment())
                .addToBackStack(null)
                .commit();

    }
    String mCategory;
    @Override
    public void toNewSource(String category) {
        this.mCategory = category;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, NewsSourceFragment.newInstance(mCategory))
                .addToBackStack(null)
                .commit();
    }
    String mTitle;
    @Override
    public void toNewsFragment(String title) {
        this.mTitle = title;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, NewsFragment.newInstance(mTitle))
                .commit();
    }
    String mLink;
    @Override
    public void toBrowser(String link) {
        this.mLink = link;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLink));
        startActivity(browserIntent);
    }
}
