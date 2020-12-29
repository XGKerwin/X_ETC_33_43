package com.example.x_etc_33_43.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.TQXX;
import com.example.x_etc_33_43.adapter.X_TQXX_adapter;
import com.example.x_etc_33_43.net.OkHttpLo;
import com.example.x_etc_33_43.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/6 11:17
 */
public class Fragment_tqxx extends Fragment {

    private ImageView img;
    private TextView time;
    private TextView txtDushu;
    private GridView gridview;
    private List<TQXX> tqxxList;
    private String shijian;
    private TextView title;
    private ImageView shuaxin;
    private X_TQXX_adapter adapter;

    private String[] arr = {"周日","周一","周二","周三","周四","周五","周六"};
    private String[] ajin = {"今天","明天","后天"};
    private List<String> strings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_tqxx, null);
        initView(view);
        strings = new ArrayList<>();

        title.setText("天气信息");
        shuaxin.setVisibility(View.VISIBLE);

        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
            }
        });

        getTime();

        getOkHttp();

        return view;
    }

    private void getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  E");
        Date date = new Date(System.currentTimeMillis());
        shijian = dateFormat.format(date);

        Calendar calendar = Calendar.getInstance();

        for (int i=0;i<6;i++){
            if (i<3){
                strings.add(calendar.get(calendar.DAY_OF_MONTH)+"日("+ajin[i]+")");
            }else {
                strings.add(calendar.get(Calendar.DAY_OF_MONTH)+"日("+arr[calendar.get(Calendar.DAY_OF_WEEK) -1]+")");
            }
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }

    }

    private void getOkHttp() {
        if (tqxxList == null){
            tqxxList = new ArrayList<>();
        }else {
            tqxxList.clear();
        }

        new OkHttpTo()
                .setUrl("get_weather_info")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        switch (jsonObject.optString("weather")){
                            case "晴":
                                img.setImageResource(R.drawable.qing);
                                break;
                            case "阴":
                                img.setImageResource(R.drawable.yin);
                                break;
                            case "小雨":
                                img.setImageResource(R.drawable.xiaoyu);
                                break;
                        }
                        time.setText(shijian);
                        txtDushu.setText(jsonObject.optString("temperature")+"度");
                        tqxxList.addAll((Collection<? extends TQXX>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<TQXX>>(){}.getType()));
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void showList() {
        adapter = new X_TQXX_adapter(tqxxList,strings);
        gridview.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shuaxin.setVisibility(View.GONE);
    }

    private void initView(View view) {
        shuaxin = getActivity().findViewById(R.id.shuaxin);
        title = getActivity().findViewById(R.id.title);
        img = view.findViewById(R.id.img);
        time = view.findViewById(R.id.time);
        txtDushu = view.findViewById(R.id.txt_dushu);
        gridview = view.findViewById(R.id.gridview);
    }
}
