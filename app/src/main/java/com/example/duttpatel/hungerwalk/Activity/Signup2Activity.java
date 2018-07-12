package com.example.duttpatel.hungerwalk.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duttpatel.hungerwalk.R;
import com.github.florent37.materialtextfield.MaterialTextField;

public class Signup2Activity extends AppCompatActivity {

    private Button signup_btn;
    private String name_str,phone_str,email_str,password_str;
    private EditText email_edittext,password_edittext;
    private MaterialTextField email_textlayout,password_layout;
    private boolean email_flag,password_flag;
    final private String email_pattern="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    final private String password_pattern="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //control
        signup_btn=(Button)findViewById(R.id.signup_signup_btn);
        email_edittext=(EditText)findViewById(R.id.signup_email_edittext);
        password_edittext=(EditText)findViewById(R.id.signup_password_edittext);
        email_textlayout=(MaterialTextField)findViewById(R.id.signup_email_textlayout);
        password_layout=(MaterialTextField)findViewById(R.id.signup_password_textlayout);


        email_flag=false;
        password_flag=false;

        //sing up code
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password_str=password_edittext.getText().toString().trim();

                validatePassword(password_str);
                
                if(email_flag==true && password_flag==true){

                }else{
                    email_textlayout.expand();
                    password_layout.expand();
                    Toast.makeText(Signup2Activity.this,"Check email and password",Toast.LENGTH_LONG).show();
                }


            }
        });

        //validate Email
        email_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                email_str=email_edittext.getText().toString().trim();
                if(email_str.length()>0){
                    email_edittext.setError(null);
                    if(email_str.matches(email_pattern)){
                        email_flag=true;
                        email_edittext.setError(null);
                    }
                    else {
                        email_edittext.setError("Invalid Email address");
                        email_flag=false;
                    }
                }
                else{
                    email_edittext.setError("Email require");
                    email_flag=false;
                }
            }
        });

    }


    private void validatePassword(String password_str) {
        if(password_str.length()>0){
            if(password_str.matches(password_pattern)){
                password_edittext.setError(null);
                password_flag=true;
            }else{
                password_edittext.setError("Password must contain  atleast one uppercase,lowercase and number");
                password_flag=false;
            }

        }else{
            password_edittext.setError("Password Require");
            password_flag=false;

        }
    }

    //override finish method


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
