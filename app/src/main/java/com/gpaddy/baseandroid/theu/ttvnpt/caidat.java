package com.gpaddy.baseandroid.theu.ttvnpt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gpaddy.baseandroid.R;
import com.gpaddy.baseandroid.theu.DAO.*;
import com.gpaddy.baseandroid.theu.adapter.IClickListener;
import com.gpaddy.baseandroid.theu.api.*;
import com.gpaddy.baseandroid.theu.api.ApiInterface;
import com.gpaddy.baseandroid.theu.model.diaphuong;
import com.gpaddy.baseandroid.theu.model.tinhModel;
import com.gpaddy.baseandroid.ui.activity.MainActivity;
import com.gpaddy.baseandroid.util.AppContain;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class caidat extends AppCompatActivity {
TextView tvtindp,tvchuyenMuc,tvtinDPChon,tvdieukhoan, tvName, tvPhone, tvAddress;
Toolbar toolbar;
chonDPBottomSheet chonDPBottomSheet;
    ArrayList<String> arrayList1=new ArrayList<>();
    ArrayList<tinhModel>tinhModels=new ArrayList<>();
    String dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);

        tvtindp=findViewById(R.id.tvtindiaphuong);
        toolbar=findViewById(R.id.toolbarcaidat);
        tvchuyenMuc=findViewById(R.id.chuyenmuc);
        tvtinDPChon=findViewById(R.id.tvdiaphuong);
        tvdieukhoan=findViewById(R.id.dieukhoan);

        tvName=findViewById(R.id.tv_name);
        tvPhone=findViewById(R.id.tv_phone);
        tvAddress=findViewById(R.id.tv_address);

        String name= Prefs.getString(AppContain.USER_NAME, "Nguyen Theu");
        String phone= Prefs.getString(AppContain.PHONE, "0762021466");
//        String address= Prefs.getString(AppContain.USER_AVATAR, "Nguyen Theu");
        tvName.setText(name);
        tvPhone.setText(phone);
//        Prefs.getBoolean(AppContain.IS_LOGIN,false)
        if(DataLocalManager.getFirstInstall()!=null){
            dp=DataLocalManager.getFirstInstall();
            tvtinDPChon.setText(dp);
            //DataLocalManager.setFirstInstall("aa");
        }
        else{
            tvtinDPChon.setText("");
        }

        tvdieukhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(caidat.this,dieukhoan.class);
                startActivity(intent);

            }
        });




        tvchuyenMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(caidat.this,danhmuc.class);
                startActivity(intent);
            }
        });





        ActionToolbar();
        LoadJson();

        tvtindp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chonDPBottomSheet=new chonDPBottomSheet(arrayList1, new IClickListener() {
                    @Override
                    public void clickIem(String string) {
                        Toast.makeText(caidat.this,string,Toast.LENGTH_LONG).show();
                        DataLocalManager.setFirstInstall(string);
                        dp=DataLocalManager.getFirstInstall();
                        tvtinDPChon.setText(dp);

                    }
                });
                chonDPBottomSheet.show(getSupportFragmentManager(),chonDPBottomSheet.getTag());

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
                Intent intent=new Intent(caidat.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void LoadJson() {//ApiClient.getInstance().getClient();
        ApiInterface apiInterface = ApiClient.getInstance().getClient().create(ApiInterface.class);
        Call<diaphuong> call;

        call=apiInterface.getTinh();
        call.enqueue(new Callback<diaphuong>() {
            @Override
            public void onResponse(Call<diaphuong> call, Response<diaphuong> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    tinhModels= (ArrayList<tinhModel>) response.body().getLtsItem();
                    for(com.gpaddy.baseandroid.theu.model.tinhModel tinhModel:tinhModels){
                        arrayList1.add(tinhModel.getTitle());

                    }
                    arrayList1.remove(arrayList1.size()-1);

                }
                else{
                    Toast.makeText(caidat.this,"No result",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<diaphuong> call, Throwable t) {

            }
        });

    }
}