package com.example.webserviceusingpicasso;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String link="https://pastebin.com/raw/wgkJgazE";
    URL url;
    HttpURLConnection httpURLConnection;
    InputStream inputStream;
   // StringBuffer stringBuffer=new StringBuffer();
    String result;
    UserDetails userDetails;
    ArrayList<UserDetails> details;
    ListView listView;
    String name;
    UserAdapter adapter;
    Button load;
    int likes;
    String profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        load=findViewById(R.id.load);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask().execute(link);
            }
        });

    }

    public  class JSONTask extends AsyncTask<String,String,ArrayList<UserDetails>>{

        @Override
        protected ArrayList<UserDetails> doInBackground(String... strings) {
            try {
                url=new URL(link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("GET");
                 inputStream= httpURLConnection.getInputStream();
              int c=0;
              StringBuffer stringBuffer=new StringBuffer();
              int responseCode=httpURLConnection.getResponseCode();
              if(responseCode==HttpURLConnection.HTTP_OK){
                   while ((c=inputStream.read())!=-1){
                       stringBuffer.append((char) c);

                  }
              }
              result=stringBuffer.toString();
              details=new ArrayList<>();
                JSONArray array=new JSONArray(result);
                for (int i=0;i<array.length();i++){
                    JSONObject object=array.getJSONObject(i);
                    likes=object.getInt("likes");
                    JSONObject object1=object.getJSONObject("user");
                    name=object1.getString("name");
                    JSONObject object2=object1.getJSONObject("profile_image");
                    profileImage=object2.getString("medium");

                    details.add(new UserDetails(name,profileImage,likes));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return details;
        }

        @Override
        protected void onPostExecute(ArrayList<UserDetails> userDetails) {
            super.onPostExecute(userDetails);
            adapter=new UserAdapter(MainActivity.this,details);
            listView.setAdapter(adapter);
        }
    }
}
