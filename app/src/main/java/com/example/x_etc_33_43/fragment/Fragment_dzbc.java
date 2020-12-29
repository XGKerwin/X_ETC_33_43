package com.example.x_etc_33_43.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.adapter.X_DZBC_adapter;
import com.example.x_etc_33_43.activity.Activity_dzbc_2;
import com.example.x_etc_33_43.activity.Activity_wddd;
import com.example.x_etc_33_43.bean.DZBC;
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
 * date   : 2020/12/7 8:19
 */
public class Fragment_dzbc extends Fragment {
    private ListView listview;
    private List<DZBC> dzbcList;
    private X_DZBC_adapter adapter;
    private TextView title;

    private TextView txt_wddd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_dzbc, null);
        initView(view);
        title.setText("定制班车");

        txt_wddd.setVisibility(View.VISIBLE);

        txt_wddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_wddd.class);
                startActivity(intent);
            }
        });



        getOkHttp();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), Activity_dzbc_2.class)
                        .putExtra("list",dzbcList.get(position));
                startActivity(intent);
            }
        });


        return view;
    }

    private void getOkHttp() {
        if (dzbcList == null) {
            dzbcList = new ArrayList<>();
        } else {
            dzbcList.clear();
        }
        new OkHttpTo()
                .setUrl("get_bus_info")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        dzbcList.addAll((Collection<? extends DZBC>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DZBC>>() {
                                }.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void show() {

        adapter = new X_DZBC_adapter(dzbcList);
        listview.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        txt_wddd.setVisibility(View.GONE);
    }

    private void initView(View view) {
        txt_wddd = getActivity().findViewById(R.id.biaoti_wddd);
        listview = view.findViewById(R.id.listview);
        title = getActivity().findViewById(R.id.title);
    }
}
