package com.example.x_etc_33_43.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.bean.DZBC;
import com.example.x_etc_33_43.bean.DZBCdingdan;
import com.example.x_etc_33_43.MainActivity;
import com.example.x_etc_33_43.R;

import java.util.List;

public class Activity_dzbc5 extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ImageView shuaxin;
    private TextView txtName;
    private TextView txtTel;
    private TextView txtDidian;
    private TextView txtRiqi;
    private Button btnTijiao;
    private DZBC dzbc;
    private String name;
    private String tel;
    private String riqi;
    private String shangche;
    private TextView biaotiWddd;
    private TextView tit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc5);
        initView();
        title.setText("确定订单");
        getdata();

        List<String> strings = dzbc.getBusline();


        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DZBCdingdan dzbCdingdan = new DZBCdingdan();
                dzbCdingdan.setLuxian(txtDidian.getText().toString());
                dzbCdingdan.setName(name);
                dzbCdingdan.setShangche(shangche);
                dzbCdingdan.setRiqi(riqi);
                dzbCdingdan.setTel(tel);
                dzbCdingdan.save();
                if (dzbCdingdan.save()) {
                    Intent intent = new Intent(Activity_dzbc5.this, MainActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Toast.makeText(Activity_dzbc5.this, "提交成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Activity_dzbc5.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getdata() {
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         *     .putExtra("list",dzbc)
         *     .putExtra("name",name)
         *     .putExtra("tel",tel)
         *     .putExtra("ri",riqi)
         *     .putExtra("didian",shangche);
         */

        dzbc = (DZBC) getIntent().getSerializableExtra("list");
        name = getIntent().getStringExtra("name");
        tel = getIntent().getStringExtra("tel");
        riqi = getIntent().getStringExtra("ri");
        shangche = getIntent().getStringExtra("didian");

        List<String> strings = dzbc.getBusline();

        tit.setText("乘车路线：" + strings.get(0) + "------" + strings.get(strings.size() - 1));
        txtDidian.setText("上车地点："+shangche);
        txtName.setText("乘客姓名：" + name);
        txtTel.setText("手机号码：" + tel);
        txtRiqi.setText("乘车日期：" + riqi);


    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        shuaxin = findViewById(R.id.shuaxin);
        txtName = findViewById(R.id.txt_name);
        txtTel = findViewById(R.id.txt_tel);
        txtDidian = findViewById(R.id.txt_didian);
        txtRiqi = findViewById(R.id.txt_riqi);
        btnTijiao = findViewById(R.id.btn_tijiao);
        biaotiWddd = findViewById(R.id.biaoti_wddd);
        tit = findViewById(R.id.tit);
    }
}