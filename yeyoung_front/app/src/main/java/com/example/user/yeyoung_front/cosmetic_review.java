package com.example.user.yeyoung_front;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class cosmetic_review extends AppCompatActivity {
    private String name;
    TextView input_oneline_review;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cosmetic_review);
        receiveIntentData();loginActivity.number += 1;
        TextView tv1=(TextView)findViewById(R.id.evaluation_product_name);
        TextView tv2 = (TextView)findViewById(R.id.evaluation_company_name);
         input_oneline_review = (TextView)findViewById(R.id.cosmetic_review_simple_review);
        ImageView img = (ImageView)findViewById(R.id.cosmetic_review_product_picture);
        if (loginActivity.number == 1){
            tv1.setText("닥터지");
            tv2.setText("메디 UV 울트라 선");
            img.setImageResource(R.drawable.suncream_default);
        }else if(loginActivity.number == 2){
            tv1.setText("예브랑");
            tv2.setText("센스티브 선크림");
            img.setImageResource(R.drawable.similiar_cosmetic1_3);
        }
        }
    public void goMain(View v){
        Intent intent = new Intent(this, mainScreenActivity.class);
        intent.putExtra("review", input_oneline_review.toString());
        startActivity(intent);
        finish();
    }
    public void receiveIntentData() {
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
    }
}
