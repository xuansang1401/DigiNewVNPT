package com.gpaddy.baseandroid.theu.ttvnpt;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gpaddy.baseandroid.R;
import com.gpaddy.baseandroid.theu.adapter.dstimkiemAdapter;
import com.gpaddy.baseandroid.theu.adapter.testAdapter;
import com.gpaddy.baseandroid.theu.api.ApiClient;
import com.gpaddy.baseandroid.theu.api.ApiInterface;
import com.gpaddy.baseandroid.theu.model.News;
import com.squareup.picasso.Picasso;

import com.gpaddy.baseandroid.theu.DAO.DatabaseNews;
import com.gpaddy.baseandroid.theu.model.Article;
import com.gpaddy.baseandroid.theu.model.DownloadModel;
import com.gpaddy.baseandroid.theu.model.SaveModel;
import com.gpaddy.baseandroid.theu.model.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chitietTin extends AppCompatActivity {
TextView title,time,description,content;
Toolbar toolbar;
ImageView imageView,imgmenu;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager1;
    private ArrayList<Article> arciles = new ArrayList<>();
    private RecyclerView recy,recy2;
Article article;
    public static final String apiKey ="3d30621d12254ce5864d0bad40094a88";

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

        recy=findViewById(R.id.recychitiettin);
        recy2=findViewById(R.id.recytukhoahotchitiet);

        layoutManager1 = new LinearLayoutManager(getApplicationContext());
        recy2.setLayoutManager(layoutManager1);

        ArrayList<String>arr=new ArrayList<>();
        arr.add("Covid");
        arr.add("Việt Nam covid");
        arr.add("Virus");
        arr.add("Prime");
        arr.add("Anthony Fauci");
        arr.add("Breakdown");
        arr.add("Wisconsin");
        arr.add("Washington");
        arr.add("Coronavirus");
        arr.add("Taehyung");
        arr.add("Nepal");
        arr.add("Animal");
        testAdapter testAdapter=new testAdapter(getApplicationContext(),arr);
        //recyclerView.setHasFixedSize(true);
        recy2.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        recy2.setAdapter(testAdapter);
        testAdapter.notifyDataSetChanged();

        ActionToolbar();

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recy.setLayoutManager(layoutManager);
        LoadJson();

        Intent intent=getIntent();
        article= (Article) intent.getSerializableExtra("thongtintin");

        title.setText(article.getTitle());
        time.setText(Utils.DateFormat(article.getPublishedAt()));
        description.setText(article.getDescription());
       // content.setText(article.getContent());
        Picasso.get().load(article.getUrlToImage()).into(imageView);

        imgmenu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
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
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void showMenu(){
        PopupMenu popupMenu=new PopupMenu(this,imgmenu);
        popupMenu.setForceShowIcon(true);//.setForceShowIcon(true);

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
    public void LoadJson() {
        ApiInterface apiInterface = ApiClient.getInstance().getClient2().create(ApiInterface.class);
        Call<News> call;

        call=apiInterface.getTinMoi("vnexpress.net",apiKey);//.getNews(keyword,"vi",);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){
                    arciles= (ArrayList<Article>) response.body().getArticles();
                    dstimkiemAdapter adapter=new dstimkiemAdapter(getApplicationContext(),arciles);
                    recy.setHasFixedSize(true);

                    recy.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
                    recy.addItemDecoration(itemDecoration);
                }
                else{
                    Toast.makeText(chitietTin.this,"No result",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

    }

}
