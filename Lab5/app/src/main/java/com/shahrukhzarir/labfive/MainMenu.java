package com.shahrukhzarir.labfive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainMenu extends AppCompatActivity implements Updater{

    private String xl;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final TextView license = (TextView)findViewById(R.id.licenseLink);
        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download(v);

            }
        });
    }

    public void update(String x){
        xl = x;
        Intent intent =new Intent();
        intent.setClass(MainMenu.this, ShowLicense.class);
        intent.putExtra("agreement",xl);
        startActivity(intent);
    }
    public void download(View view){
        Connection c = new Connection();
        c.setObserver(this);
        c.execute();
    }
}