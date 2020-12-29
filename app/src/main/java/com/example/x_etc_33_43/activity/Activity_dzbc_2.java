package com.example.x_etc_33_43.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.bean.DZBC;
import com.example.x_etc_33_43.R;

import java.util.List;

public class Activity_dzbc_2 extends AppCompatActivity {
    private DZBC dzbc;
    private ImageView caidan;
    private TextView title;
    private TextView txtTit;
    private TextView txtPiaojia;
    private TextView txtLicheng;
    private ImageView imgTu;
    private Button btnXiayibu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc_2);
        initView();
        title.setText("定制班车");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dzbc = (DZBC) getIntent().getSerializableExtra("list");
        getdata();

        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_dzbc_2.this,Activity_dzbc_3.class)
                        .putExtra("list",dzbc);
                startActivity(intent);
            }
        });


    }

    private void getdata() {
        List<String> strings = dzbc.getBusline();
        txtTit.setText(strings.get(0) + "-----" + strings.get(strings.size() - 1));
        txtPiaojia.setText("票价：￥" + dzbc.getFares() + ".0");
        txtLicheng.setText("里程：" + dzbc.getMileage() + ".0 km");
        switch (dzbc.getId()) {
            case "1":
                imgTu.setImageResource(R.drawable.ditu);
                break;
            case "2":
                imgTu.setImageResource(R.drawable.ditu2);
                break;
        }
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        txtTit = findViewById(R.id.txt_tit);
        txtPiaojia = findViewById(R.id.txt_piaojia);
        txtLicheng = findViewById(R.id.txt_licheng);
        imgTu = findViewById(R.id.img_tu);
        btnXiayibu = findViewById(R.id.btn_xiayibu);
    }
}