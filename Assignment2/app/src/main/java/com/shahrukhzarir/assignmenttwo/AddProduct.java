package com.shahrukhzarir.assignmenttwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddProduct extends AppCompatActivity {

    EditText name;
    EditText price;
    EditText description;
    ProductDBHelpher database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Intent i = getIntent();
        name = (EditText) findViewById(R.id.name);
        price = (EditText)findViewById(R.id.price);
        description = (EditText)findViewById(R.id.description);
        database = new ProductDBHelpher(this, null, null, 1);
    }

    public void addProduct(View view){
        String addName = name.getText().toString();
        String addPrice = price.getText().toString();
        String addDescription = description.getText().toString();
        database.addProduct(new Product(addName, addDescription, Integer.parseInt(addPrice)));
        setResult(1);
        finish();
    }

}