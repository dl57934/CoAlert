package com.example.user.yeyoung_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class initial_review_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_initial_review);

        Button camera=(Button)findViewById(R.id.next);
       camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(initial_review_activity.this,cpicture_list.class);
                startActivity(intent);
            }
        });

    }


}
