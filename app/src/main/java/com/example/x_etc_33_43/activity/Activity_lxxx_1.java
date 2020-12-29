package com.example.x_etc_33_43.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.LXXX;

public class Activity_lxxx_1 extends AppCompatActivity {

    private LXXX lxxx;
    private ImageView caidan;
    private TextView title;
    private ImageView imgview;
    private TextView txtMsg;
    private RatingBar rating;
    private TextView txtTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lxxx_1);
        initView();
        init();
        lxxx = (LXXX) getIntent().getSerializableExtra("1");
        switch (lxxx.getImg()){
            case "gugong1":
                imgview.setImageResource(R.drawable.gugong1);
                break;
            case "gugong2":
                imgview.setImageResource(R.drawable.gugong2);
                break;
        }

        txtMsg.setText(lxxx.getPresentation());
        txtTel.setText(lxxx.getTel());

        float s = Float.parseFloat(lxxx.getGrade());
        rating.setRating(s);
        
    }

    private void init() {
        title.setText("旅行信息");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        imgview = findViewById(R.id.imgview);
        txtMsg = findViewById(R.id.txt_msg);
        rating = findViewById(R.id.rating);
        txtTel = findViewById(R.id.txt_tel);
    }
}