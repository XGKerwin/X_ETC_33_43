package com.example.x_etc_33_43.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.CZJL;
import com.example.x_etc_33_43.net.OkHttpLo;
import com.example.x_etc_33_43.net.OkHttpTo;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activityetc_cz extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private Button btn10;
    private Button btn50;
    private Button btn100;
    private EditText edJine;
    private Button btnCz;
    private EditText edCph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityetc_cz);
        initView();
        title.setText("ETC充值");

        btn();

        btnCz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edCph.getText().toString().equals("")){
                    Toast.makeText(Activityetc_cz.this,"请输入车牌号",Toast.LENGTH_SHORT).show();
                }else if (edJine.getText().toString().equals("")) {
                        Toast.makeText(Activityetc_cz.this, "充值金额不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        String string = "鲁A" + edCph.getText().toString();
                        String jin = edJine.getText().toString();
                        setOkHttp(string,jin);
                    }
            }
        });


    }

    private void setOkHttp(final String string, final String jin) {
        //{"UserName":"user1","plate":"鲁A10001","balance":"100"}
        new OkHttpTo()
                .setUrl("set_balance")
                .setJsonObject("UserName","user1")
                .setJsonObject("plate",string)
                .setJsonObject("balance",jin)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")){
                            getTime();
                            CZJL czjl = new CZJL();
                            czjl.setCph(string);
                            czjl.setJine(jin);
                            czjl.setNumber(string.substring(6));
                            czjl.setTime(time);
                            if (czjl.save()){
                                edCph.setText("");
                                edJine.setText("");
                                Toast.makeText(Activityetc_cz.this,"充值成功",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Activityetc_cz.this,"充值失败",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Activityetc_cz.this,"未查询到此车牌",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private String time;
    private void getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        Date date = new Date(System.currentTimeMillis());
        time = dateFormat.format(date);
    }

    private void btn() {

        edJine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("0")) {
                    edJine.setText("");
                    Toast.makeText(Activityetc_cz.this, "充值不能为0", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edJine.setText("10");
            }
        });
        btn50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edJine.setText("50");
            }
        });
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edJine.setText("1000");
            }
        });
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
        btn10 = findViewById(R.id.btn_10);
        btn50 = findViewById(R.id.btn_50);
        btn100 = findViewById(R.id.btn_100);
        edJine = findViewById(R.id.ed_jine);
        btnCz = findViewById(R.id.btn_cz);
        edCph = findViewById(R.id.ed_cph);
    }
}