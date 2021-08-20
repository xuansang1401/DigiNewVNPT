package com.gpaddy.baseandroid.theu.ttvnpt;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gpaddy.baseandroid.R;
import com.squareup.picasso.Picasso;

import com.gpaddy.baseandroid.theu.DAO.DatabaseNews;
import com.gpaddy.baseandroid.theu.model.Article;
import com.gpaddy.baseandroid.theu.model.DownloadModel;
import com.gpaddy.baseandroid.theu.model.SaveModel;
import com.gpaddy.baseandroid.theu.model.Utils;

public class chitietTin extends AppCompatActivity {
TextView title,time,description,content;
Toolbar toolbar;
ImageView imageView,imgmenu;
Article article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_tin);

        title=findViewById(R.id.tvtitle);
        time=findViewById(R.id.tvtime);
        description=findViewById(R.id.tvdescription);
        content=findViewById(R.id.tvcontent);
        imageView=findViewById(R.id.imgchitiet);
        toolbar=findViewById(R.id.toolbarchitiet);
        imgmenu=findViewById(R.id.imgmenu);

        ActionToolbar();

        Intent intent=getIntent();
        article= (Article) intent.getSerializableExtra("thongtintin");

        title.setText(article.getTitle());
        time.setText(Utils.DateFormat(article.getPublishedAt()));
        description.setText(article.getDescription());
       // content.setText(article.getContent());
        Picasso.get().load(article.getUrlToImage()).into(imageView);

        imgmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();

            }
        });



    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void showMenu(){
        PopupMenu popupMenu=new PopupMenu(this,imgmenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuluutin:{
                        SaveModel saveModel=new SaveModel(article.getUrl(),article,Utils.currentDate());
                        DatabaseNews.getInstance(getApplicationContext()).daoNews().deletelsluu(Utils.currentDate(),article.getUrl());
                        DatabaseNews.getInstance(getApplicationContext()).daoNews().insertSave(saveModel);

                        Toast.makeText(getApplicationContext(),"luu tin",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case R.id.menutaitin:{
                        DownloadModel downloadModel=new DownloadModel(article.getUrl(),article,Utils.currentDate());
                        DatabaseNews.getInstance(getApplicationContext()).daoNews().deletelstai(Utils.currentDate(),article.getUrl());

                        DatabaseNews.getInstance(getApplicationContext()).daoNews().insertDownload(downloadModel);
                        Toast.makeText(getApplicationContext(),"tai tin",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case R.id.menucochu:{
                        Toast.makeText(getApplicationContext(),"Set Text Size!!",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                return false;
            }
        });
        popupMenu.show();



    }
}
