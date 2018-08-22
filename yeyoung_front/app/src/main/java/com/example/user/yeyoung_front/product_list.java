package com.example.user.yeyoung_front;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class product_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview2);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Recycler_item2> items=new ArrayList<>();
        Recycler_item2[] item=new Recycler_item2[5];
        item[0]=new Recycler_item2("듬뿍듬뿍 썬크림", "3");
        item[1]=new Recycler_item2("쫀득쫀득 썬크림", "1");
        item[2]=new Recycler_item2("말랑말랑 썬크림", "1");
        item[3]=new Recycler_item2("보들보들 썬크림", "0");
        item[4]=new Recycler_item2("반짝반짝 썬크림", "4");

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setAdapter(new RecyclerAdapter2(getApplicationContext(),items,R.layout.product_list));
    }
}