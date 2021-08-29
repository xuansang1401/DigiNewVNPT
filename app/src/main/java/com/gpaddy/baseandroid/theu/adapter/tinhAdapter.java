package com.gpaddy.baseandroid.theu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gpaddy.baseandroid.R;
import com.gpaddy.baseandroid.theu.DAO.DataLocalManager;

import java.util.ArrayList;


public class tinhAdapter extends RecyclerView.Adapter<tinhAdapter.ItemViewHolder>{
    ArrayList <String> arrayListTinh;
    IClickListener iClickListener;
    ArrayList<ItemViewHolder> dsholder=new ArrayList<>();


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
        final String tinh=arrayListTinh.get(position);
        final String data= DataLocalManager.getFirstInstall();
        if(tinh==null){
            return;
        }
        dsholder.add(holder);
        holder.check.setVisibility(View.INVISIBLE);

        holder.tv.setText(tinh);
        if(data!=null){
            if(data.equals(tinh)) {
                // Toast.makeText(context)
                holder.check.setVisibility(View.VISIBLE);
            }

        }


        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<dsholder.size();i++){
                    dsholder.get(i).check.setVisibility(View.INVISIBLE);
                }
                holder.check.setVisibility(View.VISIBLE);
                iClickListener.clickIem(tinh);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  arrayListTinh.size();
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
