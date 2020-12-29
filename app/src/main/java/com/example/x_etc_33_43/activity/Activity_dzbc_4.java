package com.example.x_etc_33_43.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.bean.DZBC;
import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.adapter.X_spinner_adapter1;

import java.util.List;

public class Activity_dzbc_4 extends AppCompatActivity {

    private DZBC dzbc;
    private String riqi;
    private ImageView caidan;
    private TextView title;
    private ImageView shuaxin;
    private TextView txtLuxian;
    private EditText edName;
    private EditText edTel;
    private Spinner spinner;
    private X_spinner_adapter1 adapter;
    private List<String> didian;
    private Button btnXia;
    private int pos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc_4);
        initView();
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("定制班车");

        dzbc = (DZBC) getIntent().getSerializableExtra("1");
        riqi = getIntent().getStringExtra("2");
        didian = dzbc.getBusline();
        showSpinner();
        getData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnXia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String tel = edTel.getText().toString();
                String shangche = didian.get(pos);
                if (name.equals("")){
                    Toast.makeText(Activity_dzbc_4.this,"请输入乘客姓名",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(Activity_dzbc_4.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Activity_dzbc_4.this, Activity_dzbc5.class)
                            .putExtra("list",dzbc)
                            .putExtra("name",name)
                            .putExtra("tel",tel)
                            .putExtra("ri",riqi)
                            .putExtra("didian",shangche);
                    startActivity(intent);
                }
            }
        });

    }

    private void getData() {
        txtLuxian.setText(didian.get(0) + "-----" + didian.get(didian.size() - 1));
    }

    private void showSpinner() {
        adapter = new X_spinner_adapter1(didian);
        spinner.setAdapter(adapter);
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        shuaxin = findViewById(R.id.shuaxin);
        txtLuxian = findViewById(R.id.txt_luxian);
        edName = findViewById(R.id.ed_name);
        edTel = findViewById(R.id.ed_tel);
        spinner = findViewById(R.id.spinner);
        btnXia = findViewById(R.id.btn_xia);
    }
}