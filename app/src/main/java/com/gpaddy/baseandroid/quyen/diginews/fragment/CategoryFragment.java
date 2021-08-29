package com.gpaddy.baseandroid.quyen.diginews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.gpaddy.baseandroid.quyen.diginews.Diaphuong;
import com.gpaddy.baseandroid.R;

public class CategoryFragment extends Fragment {
    private Button btn_diaphuong;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_fragment, container, false);
        btn_diaphuong = v.findViewById(R.id.cate_diaphuong);
        btn_diaphuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diaphuong = new Intent(CategoryFragment.this.getContext(), Diaphuong.class);
                startActivity(diaphuong);
            }
        });

        return v;
    }
}

