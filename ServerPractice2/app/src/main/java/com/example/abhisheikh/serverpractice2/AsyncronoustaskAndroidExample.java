package com.example.abhisheikh.serverpractice2;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
    
public class AsyncronoustaskAndroidExample extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asyncronoustask_android_example);
        
         
        final Button GetServerData = (Button) findViewById(R.id.GetServerData);
        
        GetServerData.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				// Server Request URL
				String serverURL = "http://192.168.137.1/sihcoding/jsonaboutschool.php";
				
		        // Create Object and call AsyncTask execute Method
				new LongOperation().execute(serverURL);
				
			}
        });	
        
    }
    
    
    // Class with extends AsyncTask class
    private class LongOperation  extends AsyncTask<String, Void, Void> {
    	
        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(AsyncronoustaskAndroidExample.this);
        TextView uiUpdate = (TextView) findViewById(R.id.output);
        TextView nameText = (TextView) findViewById(R.id.name);
        TextView aboutText = (TextView) findViewById(R.id.about);
        protected void onPreExecute() {
        	// NOTE: You can call UI Element here.
        	
        	//UI Element
        	uiUpdate.setText("Output : ");
            Dialog.setMessage("Downloading source..");
            Dialog.show();
        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {
            try {
            	
            	// Call long running operations here (perform background computation)
            	// NOTE: Don't call UI Element here.
            	
            	// Server url call by GET method
                HttpGet httpget = new HttpGet(urls[0]);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Content = Client.execute(httpget, responseHandler);
                
            } catch (ClientProtocolException e) {
                Error = e.getMessage();
                cancel(true);
            } catch (IOException e) {
                Error = e.getMessage();
                cancel(true);
            }
            
            return null;
        }
        
        protected void onPostExecute(Void unused) {
        	// NOTE: You can call UI Element here.
        	
        	// Close progress dialog
            Dialog.dismiss();
            
            if (Error != null) {
                
                uiUpdate.setText("Output : "+Error);
                
            } else {

                AboutSchool aboutSchool = jsonParse(Content);
            	uiUpdate.setText("Output : "+Content);
            	nameText.setText(aboutSchool.getName());
                aboutText.setText(aboutSchool.getAbout());
             }
        }

        protected AboutSchool jsonParse(String content){

            try {
                JSONObject baseObject = new JSONObject(content);
                JSONObject aboutSchooljson = baseObject.getJSONObject("aboutschool");
                String name = aboutSchooljson.getString("School");
                String about = aboutSchooljson.getString("About");
                return new AboutSchool(name,about);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        
    }
}