package com.gpaddy.baseandroid.theu.adapter;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;


import com.gpaddy.baseandroid.R;
import com.gpaddy.baseandroid.theu.ttvnpt.*;
import com.gpaddy.baseandroid.theu.model.*;
import com.gpaddy.baseandroid.theu.DAO.*;
import com.gpaddy.baseandroid.theu.api.*;

import java.util.ArrayList;


public class tinhAdapter extends RecyclerView.Adapter<tinhAdapter.ItemViewHolder>{
    ArrayList <String> arrayListTinh;
    //Context context;
    IClickListener iClickListener;
    String data;
    int vtri;
    ArrayList<ItemViewHolder> dsholder=new ArrayList<>();
    ItemViewHolder holder1;
    SharedPreferences sharedPreferences;//=getSharedPreferences("tindp",MODE_PRIVATE);


    public tinhAdapter(ArrayList<String> arrayListTinh, IClickListener iClickListener) {
        this.arrayListTinh = arrayListTinh;
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chondiaphuong_adapter,parent,false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        data= DataLocalManager.getFirstInstall();
            final String tinh=arrayListTinh.get(position);
            if(tinh==null){
                return;
            }
            dsholder.add(holder);

            holder.tv.setText(tinh);
            if(data!=null){
            if(data.equals(holder.tv.getText().toString())) {
                holder.check.setVisibility(View.VISIBLE);
                holder1 = holder;
                //
            }

            }
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //ItemViewHolder holder1;


                    for(int i=0;i<dsholder.size();i++){
                        dsholder.get(i).check.setVisibility(View.INVISIBLE);
                    }
//                    if(!data.equals(holder.tv.getText().toString())&& data!=null){
//                        holder1.check.setVisibility(View.INVISIBLE);
//                    }
                    iClickListener.clickIem(tinh);
                    holder.check.setVisibility(View.VISIBLE);


                }
            });
    }

    @Override
    public int getItemCount() {
        if(arrayListTinh!=null)
            return  arrayListTinh.size();

        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView check;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tvtinh);
            check=itemView.findViewById(R.id.check);

        }
    }


}
