package com.example.user.myapplication;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CallPHP {


    static class TestTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... voids) {
            return CallPHP.executeQuery("hh");

        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    public static String executeQuery(String query_string) {
        String result = "";
        HttpURLConnection urlConnection=null;
        InputStream is =null;
        try {
            URL url=new URL("http://192.192.140.211/~D10416241/test.php");                       //php的位置
            urlConnection=(HttpURLConnection) url.openConnection();//對資                料庫打開連結
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();//接通資料庫
            is=urlConnection.getInputStream();//從database 開啟 stream
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
            StringBuilder builder = new StringBuilder();
            String line = null;
            while((line = bufReader.readLine()) != null) {
                builder.append(line + "\n");
            }
            is.close();
            result = builder.toString();
        } catch(Exception e) {
            Log.e("log_tag", e.toString());
            return e.toString();
        }

        return result;
    }
}

