package com.example.user.yeyoung_front;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class searchPageCameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_search_page);

        Button camera=(Button)findViewById(R.id.activate_camera);
/*        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(
                        getApplicationContext()
                );
            }
        });*/

    }


}
