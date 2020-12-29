package com.example.x_etc_33_43.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.MainActivity;
import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.net.OkHttpLo;
import com.example.x_etc_33_43.net.OkHttpTo;

import org.json.JSONObject;

import java.io.IOException;

public class Activity_ic extends AppCompatActivity {

    private Button btnChongzhi;
    private EditText edKahao;
    private EditText edJine;
    private Button btnTuichu;
    private TextView txtYue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ic);
        initView();

        btn();

        edKahao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ss = s.toString();
                getOkHttp(ss);
            }
        });


    }

    private void btn() {
        btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cph = edKahao.getText().toString();
                String czjine = edKahao.getText().toString();
                if (cph.equals("")){
                    Toast.makeText(Activity_ic.this,"卡号不能为空",Toast.LENGTH_SHORT).show();
                }else if (czjine.equals("")){
                    Toast.makeText(Activity_ic.this,"金额不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    setOkHttp(cph,czjine);
                }
            }
        });

        btnTuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_ic.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void setOkHttp(String cph, String czjine) {

        // {"UserName":"user1","plate":"鲁A10001","balance":"100"}

        new OkHttpTo()
                .setUrl("set_balance")
                .setJsonObject("UserName","user1")
                .setJsonObject("plate",cph)
                .setJsonObject("balance",czjine)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        edJine.setText("");
                        edKahao.setText("");
                        Toast.makeText(Activity_ic.this,"充值成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getOkHttp(String ss) {
        new OkHttpTo()
                .setUrl("get_balance_b")
                .setJsonObject("UserName", "user1")
                .setJsonObject("number", ss)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String yue = jsonObject.optString("balance");
                        txtYue.setText(yue);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView() {
        btnChongzhi = findViewById(R.id.btn_chongzhi);
        edKahao = findViewById(R.id.ed_kahao);
        edJine = findViewById(R.id.ed_jine);
        btnTuichu = findViewById(R.id.btn_tuichu);
        txtYue = findViewById(R.id.txt_yue);
    }
}