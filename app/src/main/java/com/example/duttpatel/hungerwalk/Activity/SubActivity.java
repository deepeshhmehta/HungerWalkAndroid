package com.example.duttpatel.hungerwalk.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.duttpatel.hungerwalk.R;

public class SubActivity extends AppCompatActivity {

    Toolbar app_toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        app_toolbar=(Toolbar)findViewById(R.id.app_toolbar);
        ActionBar bar= getSupportActionBar();


        bar.setTitle("Sub activity");
        bar.setDisplayHomeAsUpEnabled(true);








    }
}
