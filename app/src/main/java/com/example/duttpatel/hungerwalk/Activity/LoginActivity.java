package com.example.duttpatel.hungerwalk.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duttpatel.hungerwalk.R;
import com.github.florent37.materialtextfield.MaterialTextField;

public class LoginActivity extends AppCompatActivity {



    private Button login;
    private Button recover,signup;
    private EditText email,password;
    private ImageView logo;

    private String email_str,password_str;
    static private Boolean email_flag,password_flag;

    MaterialTextField email_textlayout,password_textlayout;


    final private String email_pattern="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //init control
        login=(Button)findViewById(R.id.login_login_btn);
        signup=(Button) findViewById(R.id.login_register_btn);
        recover=(Button)findViewById(R.id.login_recover_lable);
        email=(EditText)findViewById(R.id.login_email_edittext);
        password=(EditText)findViewById(R.id.login_password_edittext);
        email_textlayout=(MaterialTextField)findViewById(R.id.login_email_textlayout);
        password_textlayout=(MaterialTextField)findViewById(R.id.login_password_textlayout);
        logo=(ImageView)findViewById(R.id.login_logo_img);



        email_flag= false;
        password_flag=false;



        //login code
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password_str=password.getText().toString().trim();
                email_str=email.getText().toString().trim();

                checkPasswordLength(password_str);

                if(email_flag==true && password_flag==true){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);

                }else{
                    email_textlayout.expand();
                    password_textlayout.expand();
                    Toast.makeText(LoginActivity.this,"Check email and password",Toast.LENGTH_LONG).show();
                }
            }
        });


        //sign up code
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginActivity.this,Signup1Activity.class);
                startActivity(intent);

            }
        });


        //recover password code
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });


        //validate email
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                email_str=email.getText().toString().trim();
                if(email_str.length()>0){
                    email.setError(null);
                    if(email_str.matches(email_pattern)){
                        email_flag=true;
                        email.setError(null);
                    }
                    else {
                        email.setError("Invalid Email address");
                        email_flag=false;
                    }
                }
                else{
                    email.setError("Email require");
                    email_flag=false;
                }

            }
        });

    }


    private  void checkPasswordLength(String password_str){
        if(password_str.length()<=0){
            password.setError("Password require");
            password_flag=false;
        }
        else{
            password.setError(null);
            password_flag=true;
        }
    }
}
