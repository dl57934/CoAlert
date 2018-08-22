package com.example.user.yeyoung_front;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class cpicture_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpicture_list);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Recycler_item> items=new ArrayList<>();
        Recycler_item[] item=new Recycler_item[5];
        item[0]=new Recycler_item(R.drawable.sun1,"듬뿍듬뿍 썬크림", "80%");
        item[1]=new Recycler_item(R.drawable.sun1,"쫀득쫀득 썬크림","90%");
        item[2]=new Recycler_item(R.drawable.sun1,"말랑말랑 썬크림","50%");
        item[3]=new Recycler_item(R.drawable.sun1,"듬뿍듬뿍 썬크림","80%");
        item[4]=new Recycler_item(R.drawable.sun1,"듬뿍듬뿍 썬크림","80%");

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),items,R.layout.activity_cpicture_list));
    }
}
