package com.example.user.yeyoung_front;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
        permissonCheck();
        Button kakaoLogin=(Button)findViewById(R.id.kakaotalkLogin);
        kakaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(
                        getApplicationContext(),    //현재 화면의 제어
                        input_skintype_activity.class);  //다음 넘어갈 클래스 지정
                startActivity(intent);  //다음화면으로 넘어간다.
                Toast.makeText(getApplicationContext(),"로그인합니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void permissonCheck() {
        int ReadStoragetPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int ReadAudioPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int WriteStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (ReadAudioPermmission != PackageManager.PERMISSION_GRANTED && ReadStoragetPermmission != PackageManager.PERMISSION_GRANTED &&
                WriteStorage != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1000);
            }
        }
    }


}
