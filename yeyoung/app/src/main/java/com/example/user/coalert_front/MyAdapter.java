package com.example.user.coalert_front;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context context;
    private List<recyclerActivity> listItems;
    private int item_layout;

    public MyAdapter(List<recyclerActivity> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_list,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recyclerActivity listItem=listItems.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getDesc());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewHead;
        public TextView textViewDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead=(TextView)itemView.findViewById(R.id.a24);
            textViewDesc=(TextView)itemView.findViewById(R.id.b_remedies);
        }
    }

}
