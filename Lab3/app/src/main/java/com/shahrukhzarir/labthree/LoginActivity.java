package com.shahrukhzarir.labthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton();
    }

    public void LoginButton(){
        final EditText username = (EditText)findViewById(R.id.enterName);
        final EditText password = (EditText)findViewById(R.id.enterPsd);
        Button LoginBtn = (Button)findViewById(R.id.LoginBtn);

        LoginBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if ("shahrukh".equals(username.getText().toString())&
                        password.getText().toString().equals("shahrukh")){
                    setResult(1);
                    finish();
                }
                else {
                    setResult(0);
                    finish();
                }
            }
        });
    }

}