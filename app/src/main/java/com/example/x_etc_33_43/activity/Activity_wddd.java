package com.example.x_etc_33_43.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.bean.DZBCdingdan;
import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.adapter.X_wddd_adapter;

import org.litepal.LitePal;

import java.util.List;

public class Activity_wddd extends AppCompatActivity {
    private List<DZBCdingdan> dzbCdingdanList;
    private ImageView caidan;
    private TextView title;
    private ListView list;
    private X_wddd_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wddd);
        initView();
        dzbCdingdanList = LitePal.findAll(DZBCdingdan.class);
        title.setText("我的订单");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new X_wddd_adapter(dzbCdingdanList);
        list.setAdapter(adapter);



    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        list = findViewById(R.id.list);
    }
}