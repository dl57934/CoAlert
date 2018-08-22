package com.example.user.yeyoung_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cosmetic_eveluation_activity extends AppCompatActivity {
    private String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costmetic_eveluation);
        receiveIntentData();



    }

    public void receiveIntentData() {
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
    }
}
