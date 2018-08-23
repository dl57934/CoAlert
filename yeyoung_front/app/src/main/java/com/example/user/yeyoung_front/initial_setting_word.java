package com.example.user.yeyoung_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class initial_setting_word extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_setting_word);

    }
    public void input_skinType(View v){
        Intent intent = new Intent(this, input_skintype_activity.class);
        startActivity(intent);
        finish();
    }
}
