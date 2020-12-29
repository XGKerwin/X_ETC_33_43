package com.example.x_etc_33_43.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.adapter.X_List_adapter;
import com.example.x_etc_33_43.bean.CLSF;
import com.example.x_etc_33_43.bean.CLSF2;
import com.example.x_etc_33_43.net.OkHttpLo;
import com.example.x_etc_33_43.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/12 11:50
 */

public class Fragment_clsf extends Fragment {

    private TextView txtRiqi;
    private ImageView imgRiqi;
    private TextView txtTime;
    private ImageView imgTime;
    private TextView txtRiqi2;
    private ImageView imgRiqi2;
    private TextView clsfTxtTime2;
    private ImageView imgTime2;
    private ListView listview;
    private Button btnChaxun;
    private Button btnTuichu;
    private List<CLSF> clsfList;
    private List<CLSF2> clsf2List;
    private X_List_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_clsf, null);
        initView(view);
        
        getOkHttp();

        btn();

        return view;
    }

    private void btn() {

        txtRiqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shTime1(txtRiqi);
            }
        });

        imgRiqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shTime1(txtRiqi);
            }
        });

        txtRiqi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shTime1(txtRiqi2);
            }
        });

        imgRiqi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shTime1(txtRiqi2);
            }
        });

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shTime(txtTime);
            }
        });

        imgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shTime(txtTime);
            }
        });

        clsfTxtTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shTime(clsfTxtTime2);
            }
        });

        imgTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shTime(clsfTxtTime2);
            }
        });

        btnChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtRiqi.getText().toString().equals("")){
                    Toast.makeText(getContext(),"请输入开始日期",Toast.LENGTH_SHORT).show();
                }else if (txtTime.getText().toString().equals("")){
                    Toast.makeText(getContext(),"请输入开始时间",Toast.LENGTH_SHORT).show();
                }else if (clsfTxtTime2.getText().toString().equals("")){
                    Toast.makeText(getContext(),"请输入结束日期",Toast.LENGTH_SHORT).show();
                }else if (txtRiqi2.getText().toString().equals("")){
                    Toast.makeText(getContext(),"请输入结束时间",Toast.LENGTH_SHORT).show();
                }else {
                    clsfList.clear();

                    String kaishi = txtRiqi.getText() + " " +txtTime.getText();
                    String jieshu = txtRiqi2.getText() + " " +clsfTxtTime2.getText();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date date = format.parse(kaishi);
                        Date date1 = format.parse(jieshu);
                        for (int i=0;i<clsfList.size();i++){
                            CLSF clsf = clsfList.get(i);
                            Date date2 = format.parse(clsf.getExit());
                            if (date2.after(date)){
                                if (date.before(date1)){
                                    clsfList.add(clsf);
                                }
                            }
                        }



                        adapter.notifyDataSetChanged();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    private void shTime1(final TextView textView) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
        timePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time1 = format.format(date);
                textView.setText(time1);
            }
        }).setType(new boolean[]{true,true,true,false,false,false}).isDialog(true).build();
        timePickerView.show();
    }

    private TimePickerView timePickerView;
    private void shTime(final TextView textView) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        timePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String S_time = simpleDateFormat.format(date);
                textView.setText(S_time);
            }
        }).setType(new boolean[]{false,false,false,true,true,true}).isDialog(true).build();
        timePickerView.show();
    }

    private void getOkHttp() {
        //{"UserName":"user1"}
        if (clsfList == null){
            clsfList = new ArrayList<>();
        }else {
            clsfList.clear();
        }
        new OkHttpTo()
                .setUrl("get_park_record")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        clsfList.addAll((Collection<? extends CLSF>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<CLSF>>(){}.getType()));
                        getOk2();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOk2() {
        if (clsf2List == null){
            clsf2List = new ArrayList<>();
        }else {
            clsf2List.clear();
        }
        new OkHttpTo()
                .setUrl("get_vehicle")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        clsf2List.addAll((Collection<? extends CLSF2>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<CLSF2>>(){}.getType()));
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showList() {
        if (adapter == null){
            adapter = new X_List_adapter(clsfList,clsf2List);
            listview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }


    private void initView(View view) {
        txtRiqi = view.findViewById(R.id.txt_riqi);
        imgRiqi = view.findViewById(R.id.img_riqi);
        txtTime = view.findViewById(R.id.txt_time);
        imgTime = view.findViewById(R.id.img_time);
        txtRiqi2 = view.findViewById(R.id.txt_riqi2);
        imgRiqi2 = view.findViewById(R.id.img_riqi2);
        clsfTxtTime2 = view.findViewById(R.id.clsf_txt_time2);
        imgTime2 = view.findViewById(R.id.img_time2);
        listview = view.findViewById(R.id.listview);
        btnChaxun = view.findViewById(R.id.clsf_btn_chaxun);
        btnTuichu = view.findViewById(R.id.clsf_btn_tuichu);
    }
}
