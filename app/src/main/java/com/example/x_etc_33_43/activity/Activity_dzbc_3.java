package com.example.x_etc_33_43.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.bean.Color1;
import com.example.x_etc_33_43.bean.DZBC;
import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.Time1;
import com.example.x_etc_33_43.adapter.X_DZBC_adapter3;

import java.util.ArrayList;
import java.util.List;

public class Activity_dzbc_3 extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ImageView shuaxin;
    private GridView gridview;
    private TextView txtShijian;
    private Button btnXiayibu;
    private DZBC dzbc;
    private List<Time1> time1List;
    private String[] yan = {"","","1","2","3","4","5",
                            "6","7","8","9","10","11","12",
                            "13","14","15","16","17","18","19",
                            "20","21","22","23","24","25","26",
                            "27","28","29","30","31","",""};

    private String[] nong = {"","","十七","十八","十九","二十","廿一",
                             "廿二","大雪","廿四","廿五","廿六","廿七","廿八",
                             "廿九","三十","冬月","初二","初三","初四", "初五",
                             "初六","冬至","初八","初九","平安夜","圣诞节","十二",
                             "十三","十四","十五","十六","十七","",""};

    private X_DZBC_adapter3 adapter3;
    private List<Color1> colorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc_3);
        initView();
        title.setText("定制班车");
        time1List = new ArrayList<>();

        getdata();

        if (colorList == null){
            colorList = new ArrayList<>();
        }else {
            colorList.clear();
        }

        for (int i=0;i<yan.length;i++){
            colorList.add(new Color1(1));
        }

        showList();

        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_dzbc_3.this, Activity_dzbc_4.class)
                        .putExtra("1",dzbc)
                        .putExtra("2",riqi);
                startActivity(intent);
            }
        });

    }

    private void showList() {
        adapter3 = new X_DZBC_adapter3(yan,nong,colorList);
        gridview.setAdapter(adapter3);
        adapter3.setMydzbc3(new X_DZBC_adapter3.Mydzbc3() {
            @Override
            public void setOn(int position) {
                Color1 color1 = colorList.get(position);
                if (color1.getColor() %2 ==0){
                    Log.i("ccccccxunazhong", "setOn: "+yan[position]);
                    getTime("2020-12-"+yan[position]);
                }else {
                    noTime("2020-12-"+yan[position]);
                    Log.i("ccccccquxiao", "setOn: "+yan[position]);
                }
                getriqi();
            }
        });
    }
    String riqi = "";
    private void getriqi() {
        riqi = "";
        for (int i=0;i<time1List.size();i++){
            Time1 time1 = time1List.get(i);
            if (riqi.equals("")){
                riqi = time1.getTime1();
            }else {
                riqi += ","+time1.getTime1();
            }
        }
        txtShijian.setText(riqi);
    }

    private void noTime(String s) {
        for (int i=0;i<time1List.size();i++){
            Time1 time1 = time1List.get(i);
            if (time1.getTime1().equals(s)){
                time1List.remove(i);
            }
        }
    }

    private void getTime(String s) {
        time1List.add(new Time1(s));
    }

    private void getdata() {
        dzbc = (DZBC) getIntent().getSerializableExtra("list");
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
        shuaxin = findViewById(R.id.shuaxin);
        gridview = findViewById(R.id.gridview);
        txtShijian = findViewById(R.id.txt_shijian);
        btnXiayibu = findViewById(R.id.btn_xiayibu);
    }
}