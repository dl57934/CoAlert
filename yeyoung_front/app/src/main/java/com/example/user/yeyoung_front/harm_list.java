package com.example.user.yeyoung_front;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class harm_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview2);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Recycler_item2> items=new ArrayList<>();
        Recycler_item2[] item=new Recycler_item2[3];
        item[0]=new Recycler_item2("UV", "3");
        item[1]=new Recycler_item2("nuclear", "1");
        item[2]=new Recycler_item2("cigaret", "1");

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setAdapter(new RecyclerAdapter2(getApplicationContext(),items,R.layout.product_list));
    }
}