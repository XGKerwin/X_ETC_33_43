package com.example.x_etc_33_43.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.activity.Activityczjl;
import com.example.x_etc_33_43.activity.Activityetc_cz;
import com.example.x_etc_33_43.activity.Activityetc_yue;
import com.example.x_etc_33_43.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/5 17:46
 */
public class Fragment_gsETC extends Fragment {

    private ImageView imgCz;
    private ImageView imgYe;
    private ImageView imgCzjl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_gsetc, null);
        initView(view);

        btn();


        return view;
    }

    private void btn() {
        imgCz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activityetc_cz.class);
                startActivity(intent);
            }
        });
        imgYe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activityetc_yue.class);
                startActivity(intent);
            }
        });
        imgCzjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activityczjl.class);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        imgCz = view.findViewById(R.id.img_cz);
        imgYe = view.findViewById(R.id.img_ye);
        imgCzjl = view.findViewById(R.id.img_czjl);
    }
}
