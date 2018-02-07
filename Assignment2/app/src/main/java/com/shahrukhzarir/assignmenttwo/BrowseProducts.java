package com.shahrukhzarir.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.net.MalformedURLException;
import java.util.List;
import java.util.ListIterator;



public class BrowseProducts extends AppCompatActivity implements ConvertObserver{

    private static final int REQUESTCODE = 1;
    double cadTemp = 0;

    private static List<Product> products;
    private  static ListIterator<Product> iterator;

    TextView productName;
    TextView canadianValue;
    TextView bitCoinValue;
    TextView description;
    ProductDBHelpher database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);

        productName = (TextView)findViewById(R.id.productName);
        canadianValue = (TextView)findViewById(R.id.cadValue);
        description = (TextView)findViewById(R.id.description);
        bitCoinValue = (TextView)findViewById(R.id.bitValue);
        database = new ProductDBHelpher(this, null,null, 1);

        products = database.getProducts();
        iterator = products.listIterator();

        try {
            nextItem(this.findViewById(android.R.id.content));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected void onStart(){
        super.onStart();
        products = database.getProducts();

    }

    public void addProduct(View view){
        Intent intent = new Intent(this, AddProduct.class);
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    public void update(String cad) {
        bitCoinValue.setText(cad);
    }

    public void convertToBitCoin(double cad) {
        Convert task = new Convert();
        task.setObserver(this);
        task.execute(new String[] {cadTemp+""});
    }

    public void onActivityResult(int reqCode, int resCode, Intent i){
        super.onActivityResult(reqCode,resCode,i);
        if(reqCode == REQUESTCODE && resCode == 1){
            iterator = products.listIterator();
        }
    }

    public void nextItem(View view) throws MalformedURLException {
        Product next;

        if(iterator.hasNext()){
            next = iterator.next();
            cadTemp = next.getPrice();
            productName.setText(next.getName());
            canadianValue.setText(Double.toString(next.getPrice()));
            description.setText(next.getDescription());
            convertToBitCoin(next.getPrice());
        }
    }

    public void prevItem(View view) throws MalformedURLException {
        Product prev;
        if(iterator.hasPrevious()){
            prev = iterator.previous();
            cadTemp = prev.getPrice();
            productName.setText(prev.getName());
            canadianValue.setText(Double.toString(prev.getPrice()));
            description.setText(prev.getDescription());
            convertToBitCoin(prev.getPrice());
        }
    }
}