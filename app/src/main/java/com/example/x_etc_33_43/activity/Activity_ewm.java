package com.example.x_etc_33_43.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_33_43.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;
import java.util.Random;

public class Activity_ewm extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private TextView txtXinxi;
    private ImageView imgEwm;
    private LinearLayout line;
    private ImageView bosImg;
    private int jine,zhouqi;
    private String Url = "";
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Random random = new Random();
            setUri(Url+random.nextInt(100));
            return false;
        }
    });

    private void setUri(String uri) {
        Hashtable<EncodeHintType,String> hashtable = new Hashtable<>();
        hashtable.put(EncodeHintType.CHARACTER_SET,"utf-8");
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(uri, BarcodeFormat.QR_CODE,300,300,hashtable);
            int [] pix = new int[300 * 300];
            for (int x=0;x<300;x++){
                for (int y = 0; y < 300; y++) {
                    if (bitMatrix.get(x, y)) {
                        pix[y * 300 + x] = 0xff000000;
                    } else {
                        pix[y * 300 + x] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pix, 0, 300, 0, 0, 300, 300);
            imgEwm.setImageBitmap(bitmap);
            bosImg.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private int looptime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewm);
        initView();
        init();
        getdata();

        Url = "车辆编号：=" + getIntent().getStringExtra("1") + "付款金额=" + getIntent().getStringExtra("2") + "元";
        txtXinxi.setText(Url);
        looptime = Integer.parseInt(getIntent().getStringExtra("2")) * 1000;       	//更新时间
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(looptime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        imgEwm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line.setVisibility(View.VISIBLE);
            }
        });

        imgEwm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                txtXinxi.setVisibility(View.VISIBLE);
                return false;
            }
        });

        bosImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line.setVisibility(View.GONE);
            }
        });

    }

    private void getdata() {

        String jine1 = getIntent().getStringExtra("1");
        String zhouqi1 = getIntent().getStringExtra("2");

        jine = Integer.parseInt(jine1);
        zhouqi = Integer.parseInt(zhouqi1);

    }

    private void init() {
        title.setText("天气信息");
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
        txtXinxi = findViewById(R.id.txt_xinxi);
        imgEwm = findViewById(R.id.img_ewm);
        line = findViewById(R.id.line);
        bosImg = findViewById(R.id.bos_img);
    }
}