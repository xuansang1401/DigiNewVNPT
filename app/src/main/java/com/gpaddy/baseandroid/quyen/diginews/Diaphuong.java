package com.gpaddy.baseandroid.quyen.diginews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.gpaddy.baseandroid.R;
import java.util.ArrayList;

public class Diaphuong extends AppCompatActivity {
    private Button btn_themDP;
    ArrayList<String> arrayList = new ArrayList<>();
    String dp;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaphuong_fragment);
        btn_themDP = findViewById(R.id.bnt_themdiaphuong);

        arrayList.add("Thái Bình");
        arrayList.add("Hà Nội");
        arrayList.add("Hải Phòng");
        arrayList.add("Quảng Ninh");
        arrayList.add("Đà Nẵng");
        arrayList.add("Bắc Giang");
        arrayList.add("Quảng Nam");
        arrayList.add("Bình Dương");
        arrayList.add("Nghệ An");
        arrayList.add("Nam Định");
        arrayList.add("Hòa Bình");

        btn_themDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
    }
}