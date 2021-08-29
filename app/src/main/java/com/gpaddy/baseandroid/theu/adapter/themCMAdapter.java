package com.gpaddy.baseandroid.theu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gpaddy.baseandroid.R;
import com.gpaddy.baseandroid.theu.DAO.DatabaseNews;
import com.gpaddy.baseandroid.theu.model.cataModel;

import java.util.ArrayList;

public class themCMAdapter extends RecyclerView.Adapter<themCMAdapter.ItemHoler> {
        Context context;
        ArrayList<cataModel> danhmuc;
public themCMAdapter(Context context, ArrayList<cataModel>danhmuc){
        this.context=context;
        this.danhmuc=danhmuc;

        }
@NonNull
@Override
public ItemHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.themcm_adapter,null);
       ItemHoler itemHodel=new ItemHoler(v);
        return itemHodel;

        }

@Override
public void onBindViewHolder(@NonNull ItemHoler holder, int position) {
//        holder.tvdm.setBackground();
        holder.tvtenCM.setText(danhmuc.get(position).getTenDMuc());//.setImageResource(danhmuc.get(position).getAnhDMuc());
        //Picasso.get().load(danhmuc.get(position).getAnhDMuc());
        //holder.tvten.setText(danhmuc.get(position).getTenDMuc());
        }

@Override
public int getItemCount() {
        return danhmuc.size();
        }

public class ItemHoler extends RecyclerView.ViewHolder {


    public TextView tvtenCM,themCM;
   // public ImageView imganh,imgitemsuacm;
    public ItemHoler(@NonNull View itemView) {
        super(itemView);
        //anh xa
        tvtenCM=itemView.findViewById(R.id.tenCM);
        themCM=itemView.findViewById(R.id.btnThemCM);
        themCM.setEnabled(true);
        //imgitemsuacm=itemView.findViewById(R.id.itemsuacm);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themCM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(context,"chon THem CM roi nha",Toast.LENGTH_SHORT).show();
                        themCM.setText("Đã thêm");
                        DatabaseNews.getInstance(context).daoNews().insertDM(danhmuc.get(getAdapterPosition()));
                        themCM.setEnabled(false);



                       themCM.setBackgroundResource(R.color.green_200);//.setBackground();
                    }
                });
                // Toast.makeText(context,tvdm.getText().toString(),Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(context, ChiTietSanPhamActivity.class);
//                    intent.putExtra("thongtinsanpham",arraysanpham.get(getAdapterPosition()));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    checkconnect.showToast_Short(context,arraysanpham.get(getAdapterPosition()).getTensp());
//                    context.startActivity(intent);
            }
        });

    }
}
}
