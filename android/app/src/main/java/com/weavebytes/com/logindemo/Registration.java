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
Class to register a new user
 */
public class Registration extends AppCompatActivity {

    Button login, register;
    EditText name, pass, birth, gender, email;
    String username = "";
    String phone = "567";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        login = (Button) findViewById(R.id.btnlogin);
        register = (Button) findViewById(R.id.btnregister);
        name = (EditText) findViewById(R.id.edtusername);
        pass = (EditText) findViewById(R.id.edtpassword);
        birth = (EditText) findViewById(R.id.edtbirth);
        gender = (EditText) findViewById(R.id.edtgender);
        email = (EditText) findViewById(R.id.edtemail);


        register.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            switch (v.getId()) {
                                                case R.id.btnregister:
                                                    username = name.getText().toString();
                                                    password = pass.getText().toString();

                                                    if (username.equals("")) {
                                                        name.setError("Username Can't Be Empty");
                                                        return;
                                                    }
                                                    if (password.equals("")) {
                                                        pass.setError("Password Can't Be Empty");
                                                        return;
                                                    }


                                                    new AsyncTask<Void, Void, String>() {
                                                        @Override
                                                        protected String doInBackground(Void... params) {

                                                            HttpClient httpClient = new DefaultHttpClient();
                                                            HttpPost httpPost = new HttpPost(Config.URL);
                                                            List<NameValuePair> nameValuePair = new ArrayList<>();
                                                            nameValuePair.add(new BasicNameValuePair("op", "register"));
                                                            nameValuePair.add(new BasicNameValuePair("username", username));
                                                            nameValuePair.add(new BasicNameValuePair("password", password));
                                                            try {
                                                                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                                                            } catch (UnsupportedEncodingException e) {
                                                                e.printStackTrace();
                                                            }
                                                            try {
                                                                HttpResponse response = httpClient.execute(httpPost);
                                                                Log.d("Http Post Response:", response.toString());
                                                                return ConvertResponse_TO_JSON.entityToString(response.getEntity());

                                                            } catch (ClientProtocolException e) {
                                                                e.printStackTrace();
                                                            } catch (IOException e) {
                                                                // Log exception
                                                                e.printStackTrace();
                                                            }
                                                            return "Bad NetWork";
                                                        }

                                                        @Override
                                                        protected void onPostExecute(String JsonString) {
                                                            JSONObject jsonobj = null;

                                                            try {
                                                                toast(JsonString);
                                                                jsonobj = new JSONObject(JsonString);


                                                                if (jsonobj.getString("error_code").equals("0")) {
                                                                    Intent intent = new Intent(Registration.this, MainActivity.class);
                                                                    //Globals.setUserId(Integer.parseInt(jsonobj.getString("uid")));
                                                                    startActivity(intent);
                                                                    finish();
                                                                } else
                                                                    Toast.makeText(Registration.this, "Username And Password Are Incorrect ", Toast.LENGTH_SHORT).show();

                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }


                                                        }
                                                    }.execute();

                                                    break;
                                            }
                                        }

                                    }
        );
    }

    public void toast(String s) {

        Toast.makeText(Registration.this, s, Toast.LENGTH_SHORT).show();
    }
}//Registration
