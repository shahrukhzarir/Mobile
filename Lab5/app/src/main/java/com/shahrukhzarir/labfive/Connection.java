package com.shahrukhzarir.labfive;

/**
 * Created by shahrukhzarir on 2017-10-24.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection extends AsyncTask<String, Void, String> {
    private Exception exception = null;
    Updater ud;
    protected String doInBackground(String... params) {
        String agreement = "";
        try {
            URL url = new URL("https://www.gnu.org/licenses/gpl.txt");
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            int result = conn.getResponseCode();

            if (result == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = "";
                while ((line = br.readLine()) != null) {
                    agreement += line + "\n";
                }
                is.close();
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agreement;
    }
    public void setObserver(Updater ud){
        this.ud = ud;
    }
    protected void onPostExecute(String a){
        ud.update(a);
    }
}