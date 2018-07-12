package com.example.duttpatel.hungerwalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duttpatel.hungerwalk.R;
import com.github.florent37.materialtextfield.MaterialTextField;

public class Signup1Activity extends AppCompatActivity {



    private EditText name,phone;
    private Button continue_btn;
    private String name_str,phone_str;
    private boolean name_flag,phone_flag;
    private MaterialTextField name_textlayout,phone_textlayout;
    final private String phone_pattern="^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);


        //control init
        name=(EditText)findViewById(R.id.signup_name_edittext);
        phone=(EditText)findViewById(R.id.signup_phonenumber_edittext);
        continue_btn=(Button)findViewById(R.id.signup_continue_btn);
        name_textlayout=(MaterialTextField)findViewById(R.id.signup_name_textlayout);
        phone_textlayout=(MaterialTextField)findViewById(R.id.signup_phonenumber_textlayout);

        name_flag=false;
        phone_flag=false;


        //continue code
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name_str=name.getText().toString().trim();
                phone_str=phone.getText().toString().trim();

                validateName(name_str);
                validatePhone(phone_str);

                if(name_flag==true && phone_flag==true){
                    Intent intent=new Intent(Signup1Activity.this,Signup2Activity.class);
                    intent.putExtra("name",name_str);
                    intent.putExtra("phone",phone_str);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_rigth,R.anim.slide_out_left);
                }else
                {
                    name_textlayout.expand();
                    phone_textlayout.expand();
                    Toast.makeText(Signup1Activity.this,"Check name and phone number",Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    private void validatePhone(String phone_str) {
        if(phone_str.length()>0){

            if(!phone_str.matches(phone_pattern)){
                phone.setError("(xxx-xxx-xxxx)");
                phone_flag=false;
            }else {
                phone.setError(null);
                phone_flag=true;
            }
        }else{
            phone.setError("Phone require");
            phone_flag=false;
        }
    }

    private void validateName(String name_str) {

        if(name_str.length()<=0){
            name.setError("Name Require");
            name_flag=false;
        }
        else
        {
            name.setError(null);
            name_flag=true;
        }
    }
}
