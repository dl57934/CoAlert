package com.example.user.yeyoung_front;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class mainScreenActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    selectedFragment=new searchFragment();
                    break;
                case R.id.navigation_add_review:
                   selectedFragment=new addReviewFragment();
                    break;
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    break;
                case R.id.navigation_bad_elements:
                    selectedFragment=new harmFragment();
                    break;
                case R.id.navigation_mypage:
                    mTextMessage.setText(R.string.title_mypage);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.navigation_container,selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}

