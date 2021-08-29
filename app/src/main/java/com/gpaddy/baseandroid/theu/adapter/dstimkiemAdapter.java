package com.gpaddy.baseandroid.theu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gpaddy.baseandroid.R;
import com.gpaddy.baseandroid.theu.DAO.DatabaseNews;
import com.gpaddy.baseandroid.theu.model.Article;
import com.gpaddy.baseandroid.theu.model.HistoryModel;
import com.gpaddy.baseandroid.theu.model.Utils;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class dstimkiemAdapter extends RecyclerView.Adapter<dstimkiemAdapter.ItemHoler> {
    Context context;
    ArrayList<Article> articles;


    public dstimkiemAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles=articles;

    }

    @NonNull
    @Override
    public ItemHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dstimkiem_adapter, null);
        ItemHoler itemHodel = new ItemHoler(v);
        return itemHodel;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHoler holder, int position) {
        Article model = articles.get(position);
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(Utils.getRandomDrawbleColor());
//        requestOptions.error(Utils.getRandomDrawbleColor());
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
//        requestOptions.centerCrop();
//
//        Glide.with(context)
//                .load(model.getThumbnail())
//                .apply(requestOptions)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                }).transition(DrawableTransitionOptions.withCrossFade()).into(holder.img);
        Picasso.get().load(model.getUrlToImage()).into(holder.img);
        holder.tieude.setText(model.getTitle());
        holder.tgian.setText(Utils.DateFormat(model.getPublishedAt()));
        holder.tenbao.setText("vn express");
        //  holder.socmt.setText(" \u2022" + Utils.DateToTimeFormat(model.getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ItemHoler extends RecyclerView.ViewHolder {
        //public TextView tvdm;
        ImageView img;
        TextView tenbao, tgian, tieude, socmt;

        public ItemHoler(@NonNull View itemView) {
            super(itemView);
            //anh xa

            tgian = itemView.findViewById(R.id.tvtgiandstk);
            tieude = itemView.findViewById(R.id.tvtieudedstk);
            tenbao = itemView.findViewById(R.id.tvtenbaodstk);
            img = itemView.findViewById(R.id.imgdstk);
            socmt = itemView.findViewById(R.id.socmt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "hahahahehehe", Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(context, chitietTin.class);
//                    intent.putExtra("thongtintin",articles.get(getAdapterPosition()));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);

//                    HistoryModel historyModel=new HistoryModel(articles.get(getAdapterPosition()).getUrl(),articles.get(getAdapterPosition()),Utils.currentDate());
//                    DatabaseNews.getInstance(context).daoNews().deletelsxem(Utils.currentDate(),articles.get(getAdapterPosition()).getUrl());

//                    Toast.makeText(context,historyModel.getTimeHistory()+"",Toast.LENGTH_LONG).show();
//                    DatabaseNews.getInstance(context).daoNews().insertHistory(historyModel);
                }
            });

        }
    }
}

