package com.example.x_etc_33_43.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.bean.GSLK;
import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.adapter.X_GSLK_adapter;
import com.example.x_etc_33_43.net.OkHttpLo;
import com.example.x_etc_33_43.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 17:22
 */
public class Fragment_gslk extends Fragment {

    private ViewFlipper ViewFlipper;
    private ExpandableListView elistview;
    private List<GSLK> gslkList;
    private X_GSLK_adapter adapter;
    private List<New> news;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_gslk, null);
        initView(view);
        title.setText("高速路况");

        elistview.setGroupIndicator(null);

        getnew();
        getOkHttp();


        return view;
    }

    private void getnew() {
        if (news == null){
            news = new ArrayList<>();
        }else {
            news.clear();
        }
        new OkHttpTo()
                .setUrl("get_news")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        news.addAll((Collection<? extends New>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<New>>(){}.getType()));
                        showtxt();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void showtxt() {
        for (int i=0;i<news.size();i++){
            final New n = news.get(i);
            TextView textView = new TextView(getContext());
            textView.setText(n.getTitle());
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(30);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            ViewFlipper.addView(textView);
        }
        ViewFlipper.setFlipInterval(3000);
        ViewFlipper.startFlipping();
    }

    private void shownew(String title, String message) {


    }

    private void getOkHttp() {
        if (gslkList == null){
            gslkList = new ArrayList<>();
        }else {
            gslkList.clear();
        }
        new OkHttpTo()
                .setUrl("get_roads")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        gslkList.addAll((Collection<? extends GSLK>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GSLK>>(){}.getType()));
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showList() {
        adapter = new X_GSLK_adapter(gslkList);
        elistview.setAdapter(adapter);
    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        ViewFlipper = view.findViewById(R.id.ViewFlipper);
        elistview = view.findViewById(R.id.elistview);
        ViewFlipper.startFlipping();
    }
}
