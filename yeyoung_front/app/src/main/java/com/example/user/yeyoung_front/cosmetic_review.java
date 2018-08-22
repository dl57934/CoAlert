package com.example.user.yeyoung_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cosmetic_review extends AppCompatActivity {
    private String name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cosmetic_review);
        receiveIntentData();


        TextView tv1=(TextView)findViewById(R.id.evaluation_product_name);
        tv1.setText(name);


    }

    public void receiveIntentData() {
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
    }
}
