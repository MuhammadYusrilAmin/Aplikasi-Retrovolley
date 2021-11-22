package com.example.retrofit3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.retrofit3.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actionRetrofit(View view) {
        Intent retrofit = new Intent(this, IndexActivity.class);
        startActivity(retrofit);
    }

    public void actionVolley(View view) {
        Intent volley = new Intent(this, VolleyActivity.class);
        startActivity(volley);
    }
}