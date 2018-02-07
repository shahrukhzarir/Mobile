package com.shahrukhzarir.labfive;

import com.shahrukhzarir.labfive.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class ShowLicense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_license);

        Intent intent = getIntent();
        String agreement = intent.getStringExtra("agreement");

        TextView textView = (TextView)findViewById(R.id.agreement);
        textView.setText(agreement);

        Button close = (Button)findViewById(R.id.close);
        close.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent returnIntent = new Intent();
                returnIntent.setClass(ShowLicense.this, MainMenu.class);
                startActivity(returnIntent);
            }
        });
    }
}

