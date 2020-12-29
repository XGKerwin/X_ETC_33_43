package com.example.x_etc_33_43.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.adapter.X_Listview_adapter;
import com.example.x_etc_33_43.bean.CZJL;

import org.litepal.LitePal;

import java.util.List;

public class Activityczjl extends AppCompatActivity {

    private TextView txtSum;
    private ListView listview;
    private TextView title;
    private List<CZJL> czjlList;
    private X_Listview_adapter adapter;
    private ImageView caidan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityczjl);
        initView();

        title.setText("充值记录");

        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        czjlList = LitePal.findAll(CZJL.class);

        getjinte();

        showListview();

    }

    private void showListview() {
        adapter = new X_Listview_adapter(czjlList);
        listview.setAdapter(adapter);
    }

    private void getjinte() {
        if (czjlList.size() == 0) {
            return;
        }
        int jine = 0;
        for (int i = 0; i < czjlList.size(); i++) {
            CZJL czjl = czjlList.get(i);
            int jin = Integer.parseInt(czjl.getJine());
            jine += jin;
        }
        txtSum.setText("充值合计：" + jine + "元");
    }

    private void initView() {
        title = findViewById(R.id.title);
        txtSum = findViewById(R.id.txt_sum);
        listview = findViewById(R.id.listview);
        caidan = findViewById(R.id.caidan);
    }
}