package com.example.user.yeyoung_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //화면 전환 - 인텐트 날리기 (startActivity)
        //1. 다음 넘어갈 화면을 준비한다 (layout xml, java)
        //2. AndroidManifest.xml에 Activity를 등록한다.
        //3. Intent 객체를 만들어서 startActivity 한다.

        Button kakaoLogin=(Button)findViewById(R.id.kakaotalkLogin);
        kakaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(
                        getApplicationContext(),    //현재 화면의 제어
                        initialCameraActivity.class);  //다음 넘어갈 클래스 지정
                startActivity(intent);  //다음화면으로 넘어간다.
                Toast.makeText(getApplicationContext(),"로그인합니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }



}
