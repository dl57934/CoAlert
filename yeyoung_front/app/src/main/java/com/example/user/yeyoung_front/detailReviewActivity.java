package com.example.user.yeyoung_front;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class detailReviewActivity extends AppCompatActivity implements View.OnClickListener{
   //사진으로 전송 시 되돌려 받을 번호
    static int REQUEST_PICTURE=1;
    //앨범으로 전송 시 돌려받을 번호
   static int REQUEST_PHOTO_ALBUM=2;
   //첫 번째 이미지 아이콘 샘플이다.
   static String SAMPLEIMG="ic_launcher.png";

   Button picBtn;
   Dialog dialog;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detail_review);

        Button back=(Button)findViewById(R.id.detail_review_back);
/*        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(
                        getApplicationContext(),
                        *//*간단리뷰창으로 넘어가기*//*
                );
                startActivity(intent);
            }
        });*/

        //여기에 일단 기본적인 이미지파일 하나를 가져온다.
        picBtn=(Button)findViewById(R.id.add_picture1);
        //가져올 사진의 이름을 정한다.
        findViewById(R.id.add_picture1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //첫 번째로 사진가져오기를 클릭하면 또 다른 레이아웃 것을 다이어로그로 출력해서 선택하게끔 하자
        if(v.getId()== R.id.add_picture1){
            //다이어로그를 먼저 만들어낸다.
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //이곳에 만드는 다이어로그의 layout을 정한다.
           // View customLayout=View.inflate(this,R.layout.custom_button,null);
        }
    }
}
