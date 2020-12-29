package com.example.x_etc_33_43.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.XWMT;
import com.example.x_etc_33_43.adapter.X_XWMT_adapter;
import com.example.x_etc_33_43.net.OkHttpLo;
import com.example.x_etc_33_43.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/7 15:45
 */
public class Fragment_xwmt extends Fragment {

    private TextView txtKeji;
    private TextView txtJiaoyu;
    private TextView txtTiyu;
    private ListView listview;
    private TextView title;
    private String type = "1";
    private List<XWMT> xwmtList;
    private X_XWMT_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_xwmt, null);
        initView(view);
        title.setText("新闻媒体");
        btn();

        getOkHttp();

        return view;
    }

    private void getOkHttp() {
        /**
         * {"UserName":"user1","type":1}
         */

        if (xwmtList == null){
            xwmtList = new ArrayList<>();
        }else {
            xwmtList.clear();
        }

        new OkHttpTo()
                .setUrl("get_all_news")
                .setJsonObject("UserName","user1")
                .setJsonObject("type",type)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        xwmtList.clear();
                        JSONArray jsonArray = jsonObject.optJSONArray("news");
                        for (int i=0;i<jsonArray.length();i++){
                            xwmtList.add(new XWMT(jsonArray.optString(i)));
                        }
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        if (adapter == null){
            adapter = new X_XWMT_adapter(xwmtList);
            listview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    private void btn() {
        txtKeji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "1";
                getOkHttp();
                txtKeji.setBackgroundResource(R.drawable.huang_xian);
                txtJiaoyu.setBackgroundResource(R.drawable.wu);
                txtTiyu.setBackgroundResource(R.drawable.wu);
            }
        });

        txtJiaoyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "2";
                getOkHttp();
                txtKeji.setBackgroundResource(R.drawable.wu);
                txtJiaoyu.setBackgroundResource(R.drawable.huang_xian);
                txtTiyu.setBackgroundResource(R.drawable.wu);
            }
        });

        txtTiyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "3";
                txtKeji.setBackgroundResource(R.drawable.wu);
                txtJiaoyu.setBackgroundResource(R.drawable.wu);
                txtTiyu.setBackgroundResource(R.drawable.huang_xian);
                getOkHttp();
            }
        });

    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        txtKeji = view.findViewById(R.id.txt_keji);
        txtJiaoyu = view.findViewById(R.id.txt_jiaoyu);
        txtTiyu = view.findViewById(R.id.txt_tiyu);
        listview = view.findViewById(R.id.listview);
    }
}
