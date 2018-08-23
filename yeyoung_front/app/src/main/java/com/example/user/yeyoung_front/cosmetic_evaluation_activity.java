package com.example.user.yeyoung_front;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class cosmetic_evaluation_activity extends AppCompatActivity {
    private String name;

    Handler defaultRatingBarHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costmetic_evaluation);
        receiveIntentData();
        loginActivity.number += 1;
        Button btn = findViewById(R.id.evaluation_confirmed_button);
        ImageView img = findViewById(R.id.evaluation_prod_pic);
        TextView cosmetic_name = findViewById(R.id.evaluation_product_name);
        if (loginActivity.number == 1){
            img.setImageResource(R.drawable.suncream_default);
        }else if(loginActivity.number == 2){
            img.setImageResource(R.drawable.similiar_cosmetic1_3);
        }
        cosmetic_name.setText(name);
        Log.e("discharge: ", String.valueOf(loginActivity.number));
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(cosmetic_evaluation_activity.this, mainScreenActivity.class);
                startActivity(intent);
            }

        });

        setTitle("dev2qa.com - RatingBar Example");

        // Get rating bar.
        final RatingBar ratingBarDefault = (RatingBar) findViewById(R.id.fisrt_eveluation_ratingbar);

        // rating bar can not be modified manually.
        ratingBarDefault.setIsIndicator(false);

        defaultRatingBarHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    float currRating = ratingBarDefault.getRating();
                    if (currRating == ratingBarDefault.getNumStars()) {
                        currRating = 0;
                    }
                    float stepSize = ratingBarDefault.getStepSize();

                    float newRating = currRating + stepSize;
                    ratingBarDefault.setRating(newRating);
                }
            }
        };

    }

    public void receiveIntentData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
    }
}
