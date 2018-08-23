package com.example.user.yeyoung_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class input_skintype_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_input_skintype);
        Button btn1=(Button)findViewById(R.id.dry_button);
        Button btn2=(Button)findViewById(R.id.oily_button);
        Button btn3=(Button)findViewById(R.id.normal_button);
        Button btn4=(Button)findViewById(R.id.combination_button);
        Button btn5=(Button)findViewById(R.id.sensetive_button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(input_skintype_activity.this,initialReviewActivity.class);
                Toast.makeText(getApplicationContext(), "건성 선택", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(input_skintype_activity.this,initialReviewActivity.class);
                Toast.makeText(getApplicationContext(), "지성 선택", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(input_skintype_activity.this,initialReviewActivity.class);
                Toast.makeText(getApplicationContext(), "중성 선택", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(input_skintype_activity.this,initialReviewActivity.class);
                Toast.makeText(getApplicationContext(), "복합성 선택", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(input_skintype_activity.this,initialReviewActivity.class);
                Toast.makeText(getApplicationContext(), "민감성 선택", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }

}