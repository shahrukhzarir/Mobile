package com.shahrukhzarir.labthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutButton = (Button)findViewById(R.id.aboutButton);
        Button loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        aboutButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            Toast.makeText(this,"User/Password is correct",
                    Toast.LENGTH_SHORT).show();}
        else if(resultCode == 0) {
            Toast.makeText(this,"User/Password is incorrect",
                    Toast.LENGTH_SHORT).show();}
    }
}