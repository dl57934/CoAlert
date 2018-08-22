package com.example.user.yeyoung_front;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {
    Context context;
    List<Recycler_item2> items;
    int item_layout;
    public RecyclerAdapter2(Context context, List<Recycler_item2> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview2,null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Recycler_item2 item=items.get(position);
        holder.name.setText(item.getName());
        holder.number.setText(item.getNumber());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context,item.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,cosmetic_review.class);
                intent.putExtra("name",item.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
           name=(TextView)itemView.findViewById(R.id.name);
            number=(TextView)itemView.findViewById(R.id.number);
        }

    }

}