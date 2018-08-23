package com.example.user.yeyoung_front;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class mainScreenActivity extends AppCompatActivity{

    private TextView mTextMessage;
    int num=0;
    ImageButton img=null;
    Integer[] cosmeticID;
    String[] cname;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        mTextMessage=findViewById(R.id.text);

        TextView badElement = (TextView)findViewById(R.id.bad_element);
        if (loginActivity.number == 0){

            cosmeticID = new Integer[]{R.drawable.recommaned_cosmetic0_0, R.drawable.recommaned_cosmetic0_1, R.drawable.recommaned_cosmetic0_2};
            badElement.setText("멘톨, 페퍼민트잎");
            cname = new String[]{"퓨어 미네랄 선크림 SPF50+ PA++++","비타민 워터리 선 젤 SPF50+ PA++++","산소수 라이트 선 젤 SPF50+ PA++++"};
        }else if(loginActivity.number == 1){
            cosmeticID = new Integer[]{R.drawable.recommaned_cosmetic1_0, R.drawable.recommaned_cosmetic1_1, R.drawable.recommaned_cosmetic1_2};
            badElement.setText("향료, 피이지-8");
            cname=new String[]{"UV 아쿠아 리치 워터리 에센스 선크림 SPF50+", "에코 어스 파워 노 세범 선 크림 SPF50+ PA+++","선 메이트 프로텍터 SPF50+ PA+++"};
        }else if(loginActivity.number == 2){
            cosmeticID = new Integer[]{R.drawable.recommaned_cosmetic2_0, R.drawable.recommaned_cosmetic2_1, R.drawable.recommaned_cosmetic2_2};
            badElement.setText("향료, 페녹시에탄올");
            cname=new String[]{"마일드 프로텍트 에센스 선 SPF50+ PA++++", "메이크업 선 크림 SPF50+ PA+++","선 프라이즈 마일드 에어리 피니쉬"};

        }

        Button Lbtn=(Button)findViewById(R.id.LeftBtn);
        Button Rbtn=(Button)findViewById(R.id.RightBtn);;
        name=(TextView)findViewById(R.id.nametext);


        img = (ImageButton) findViewById(R.id.imgbtn);
        img.setImageResource(cosmeticID[num]);
        name.setText(cname[num]);
        img.setBackground(new ShapeDrawable(new OvalShape()));
        img.setBackground(getResources().getDrawable(R.drawable.main_circle));
//        img.setClipToOutline(true);
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(
//                        getApplicationContext(), loginActivity.class);
//                startActivity(intent);  //다음화면으로 넘어간다.
//                //Toast.makeText(getApplicationContext(), "..", Toast.LENGTH_SHORT).show();
//            }
//        });

        Lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num==0)
                    num=2;
                else
                    num-=1;
                img.setImageResource(cosmeticID[num]);
                name.setText(cname[num]);
            }
        });

        Rbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num =(num+1)%3;
                img.setImageResource(cosmeticID[num]);
                name.setText(cname[num]);
                // Toast.makeText(getApplicationContext(), num, Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    selectedFragment=new searchFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.navigation_container,selectedFragment).commit();
                    break;
                case R.id.navigation_add_review:

                    Intent intent = new Intent(mainScreenActivity.this, initialReviewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_home:

                case R.id.navigation_bad_elements:
                    selectedFragment=new harmFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.navigation_container,selectedFragment).commit();
                    break;
                case R.id.navigation_mypage:
                    mTextMessage.setText(R.string.title_mypage);
                    break;
            }

            return true;
        }
    };


}