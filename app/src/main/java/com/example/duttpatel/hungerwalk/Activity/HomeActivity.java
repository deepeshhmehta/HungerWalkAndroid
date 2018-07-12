package com.example.duttpatel.hungerwalk.Activity;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.duttpatel.hungerwalk.R;

public class HomeActivity extends AppCompatActivity {


    private Button login;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //control init
        login=(Button)findViewById(R.id.home_login_btn);
        logo=(ImageView)findViewById(R.id.imageView3);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                ActivityOptionsCompat options= ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this,logo, ViewCompat.getTransitionName(logo));
                startActivity(intent,options.toBundle());

            }
        });

    }
}
