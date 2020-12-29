package com.example.x_etc_33_43.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.net.OkHttpLo;
import com.example.x_etc_33_43.net.OkHttpTo;

import org.json.JSONObject;

import java.io.IOException;

public class Activityetc_yue extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private EditText edBianhao;
    private Button btnCx;
    private TextView txtYue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityetc_yue);
        initView();
        title.setText("ETC余额");
        btn();

        btnCx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edBianhao.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(Activityetc_yue.this, "请输入车牌编号", Toast.LENGTH_SHORT).show();
                } else {
                    setOkHttp(s);
                }
            }
        });


    }

    private void setOkHttp(String bianhao) {
        //{"UserName":"user1","number":"1"}
        new OkHttpTo()
                .setUrl("get_balance_b")
                .setJsonObject("UserName", "user1")
                .setJsonObject("number", bianhao)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")){
                            txtYue.setText(jsonObject.optString("balance"));
                            Toast.makeText(Activityetc_yue.this,"查询成功",Toast.LENGTH_SHORT).show();
                            edBianhao.setText("");
                        }else {
                            Toast.makeText(Activityetc_yue.this,"未找到此编号",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void btn() {
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
        edBianhao = findViewById(R.id.ed_bianhao);
        btnCx = findViewById(R.id.btn_cx);
        txtYue = findViewById(R.id.txt_yue);
    }
}