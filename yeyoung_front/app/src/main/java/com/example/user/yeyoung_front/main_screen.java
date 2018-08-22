package com.example.user.yeyoung_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class main_screen extends AppCompatActivity {

    Integer[] cosmeticID = {R.drawable.suncream, R.drawable.suncream2, R.drawable.suncream3, R.drawable.suncream4};
    int num=0;
    ImageButton img=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        //화면 전환 - 인텐트 날리기 (startActivity)
        //1. 다음 넘어갈 화면을 준비한다 (layout xml, java)
        //2. AndroidManifest.xml에 Activity를 등록한다.
        //3. Intent 객체를 만들어서 startActivity 한다.

        ImageButton Lbtn = (ImageButton) findViewById(R.id.Leftbtn);
        ImageButton Rbtn = (ImageButton) findViewById(R.id.Rightbtn);


        img = (ImageButton) findViewById(R.id.imgbtn);
        img.setImageResource(cosmeticID[num]);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(), initialCameraActivity.class);
                startActivity(intent);  //다음화면으로 넘어간다.
                //Toast.makeText(getApplicationContext(), "..", Toast.LENGTH_SHORT).show();
            }
        });

        Lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num==0)
                    num=3;
                else
                    num-=1;
                img.setImageResource(cosmeticID[num]);
            }
        });

        Rbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num =(num+1)%4;
                img.setImageResource(cosmeticID[num]);
               // Toast.makeText(getApplicationContext(), num, Toast.LENGTH_SHORT).show();
            }
        });


    }


}
