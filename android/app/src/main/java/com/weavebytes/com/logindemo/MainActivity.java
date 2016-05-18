package com.weavebytes.com.logindemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/*
class to login with username and password
 */
public class MainActivity extends AppCompatActivity {

    Button login, register;
    EditText name, pass;
    String username = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.btnlogin);
        register = (Button) findViewById(R.id.btnregister);
        name = (EditText) findViewById(R.id.edtusername);
        pass = (EditText) findViewById(R.id.edtpassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hide();

                username = name.getText().toString();
                password = pass.getText().toString();

                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {

                        if (isOnline()) {

                            HttpClient httpClient = new DefaultHttpClient();
                            HttpPost httpPost     = new HttpPost(Config.URL);
                            List<NameValuePair> nameValuePair = new ArrayList<>();
                            nameValuePair.add(new BasicNameValuePair("op", "login"));
                            nameValuePair.add(new BasicNameValuePair("username", username));
                            nameValuePair.add(new BasicNameValuePair("password", password));

                            try {
                                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                            } catch (UnsupportedEncodingException e) {
                                return e + "";
                            }
                            try {
                                HttpResponse response = httpClient.execute(httpPost);
                                Log.d("Http Post Response:", response.toString());
                                //Converting Response To JsonString
                                return ConvertResponse_TO_JSON.entityToString(response.getEntity());
                            } catch (ClientProtocolException e) {
                                // Log exception
                                e.printStackTrace();
                            } catch (IOException e) {
                                // Log exception
                                e.printStackTrace();
                            }
                            return "Bad NetWork";
                        } else {
                            return "Check Your Connection";

                        }
                    }

                    @Override
                    protected void onPostExecute(String JsonString) {
                        toast(JsonString);
                        JSONObject jsonobj = null;
                        try {

                            jsonobj = new JSONObject(JsonString);


                            //Parsing JSON and Checking the error_code (username ot password are correct or not)

                            if (jsonobj.getString("error_code").equals("0")) {

                                Intent intent = new Intent(MainActivity.this, DashBoard.class);
                                intent.putExtra("name",username);
                                startActivity(intent);
                                finish();
                            } else {
                                toast(jsonobj.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //toast(e + " ");
                        }
                    }
                }.execute();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,Registration.class);
                startActivity(intent);

            }
        });
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL("http://www.google.com");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(2000);
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return new Boolean(true);
                }
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public void toast(String s) {
        Toast.makeText(MainActivity.this,"invalid username or password", Toast.LENGTH_SHORT).show();
    }

    public void hide() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(login.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//MainActivity

}
