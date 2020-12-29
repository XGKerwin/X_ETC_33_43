package com.example.x_etc_33_43.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.activity.Activity_lxxx_1;
import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.adapter.X_LXXX_adapter;
import com.example.x_etc_33_43.bean.LXXX;
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
 * date   : 2020/12/6 9:56
 */
public class Fragment_lxxx extends Fragment {
    private GridView gridview;
    private TextView title;
    private List<LXXX> lxxxList;
    private X_LXXX_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_lxxx, null);
        initView(view);
        title.setText("旅行信息");
        
        getOkHttp();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), Activity_lxxx_1.class)
                        .putExtra("1",lxxxList.get(position));
                startActivity(intent);
            }
        });


        return view;
    }

    private void getOkHttp() {
        if (lxxxList == null){
            lxxxList = new ArrayList<>();
        }else {
            lxxxList.clear();
        }
        new OkHttpTo()
                .setUrl("get_scenic_spot")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        lxxxList.addAll((Collection<? extends LXXX>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<LXXX>>(){}.getType()));
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showList() {
        adapter = new X_LXXX_adapter(lxxxList);
        gridview.setAdapter(adapter);
    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        gridview = view.findViewById(R.id.gridview);
    }
}
