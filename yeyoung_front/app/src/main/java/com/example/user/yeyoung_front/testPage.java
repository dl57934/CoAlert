package com.example.user.yeyoung_front;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.Toast;

public class testPage extends AppCompatActivity {
RatingBar rb;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_costmetic_eveluation);

        rb=(RatingBar)findViewById(R.id.fisrt_eveluation_ratingbar);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(),Float.toString(rating), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
