package com.example.abhisheikh.serverpost3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new PostRequest().execute();
    }

    public void makePostRequest(){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://192.168.137.1/httppost.php");

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
        nameValuePairs.add(new BasicNameValuePair("name","hello"));
        nameValuePairs.add(new BasicNameValuePair("user","abc"));
        nameValuePairs.add(new BasicNameValuePair("email","def"));
        nameValuePairs.add(new BasicNameValuePair("pass","hi"));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            Log.d("Entity", "Entity set");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse response = httpClient.execute(httpPost);
            Log.d("Http Post Response", response.toString());
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class PostRequest extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params) {
            makePostRequest();
            return null;
        }
    }
}
