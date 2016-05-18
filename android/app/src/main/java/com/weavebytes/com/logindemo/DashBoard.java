package com.weavebytes.com.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

//main board
public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        String s = (String)bd.get("name");
        Toast.makeText(DashBoard.this, "wellcome " + s , Toast.LENGTH_SHORT).show();
    }
}//DashBoard
