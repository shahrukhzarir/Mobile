package com.shahrukhzarir.assignmenttwo;

/**
 * Created by shahrukhzarir on 2017-11-15.
 */

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nova on 2017-11-15.
 */

public class Convert extends AsyncTask<String, Void, String> {
    private ConvertObserver browseProducts;

    public void setObserver(ConvertObserver x){
        browseProducts = x;
    }
    @Override
    protected String doInBackground(String... cadValue) {
        String output="";
        try {
            URL url = new URL("https://blockchain.info/tobtc?currency=CAD&value="+cadValue[0]);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            InputStream inputRaw = conn.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputRaw));

            String line="";
            while ((line = input.readLine()) != null) {
                output += line;
                System.out.println(line);
            }
            inputRaw.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
    public void onPostExecute(String lines){
        browseProducts.update(lines);
    }
}