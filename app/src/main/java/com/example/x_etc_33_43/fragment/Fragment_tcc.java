package com.example.x_etc_33_43.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.adapter.X_spinner_adapter;
import com.example.x_etc_33_43.bean.TCC;
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
 * date   : 2020/12/11 16:59
 */
public class Fragment_tcc extends Fragment {


    private List<TCC> tccList;
    private String[] arr = {"南京学院停车场", "德州百货停车场", "德州职业停车场"};
    private X_spinner_adapter adapter;
    private ImageView img;
    private Spinner spinner;
    private TextView txtSf;
    private TextView txtSycw;
    private Button btnShuaxin;
    private String S_tingche = "南京学院停车场";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_tcc, null);
        initView(view);

        spinneradapter();
        spinnerdianji();

        getOkHttp();

        btnShuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
            }
        });


        return view;
    }

    private void getOkHttp() {
        if (tccList == null) {
            tccList = new ArrayList<>();
        } else {
            tccList.clear();
        }
        new OkHttpTo()
                .setUrl("get_park_data")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tccList.addAll((Collection<? extends TCC>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<TCC>>() {
                                }.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void show() {
        for (int i=0;i<tccList.size();i++){
            TCC tcc = tccList.get(i);
            if (tcc.getParking_name().equals(S_tingche)){
                txtSf.setText("收费标准："+tcc.getPrice());
                txtSycw.setText("剩余车位："+tcc.getCarport()+"/1000");
            }
        }
    }

    private void spinnerdianji() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                S_tingche = arr[position];
                getOkHttp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void spinneradapter() {
        adapter = new X_spinner_adapter(arr);
        spinner.setAdapter(adapter);
    }

    private void initView(View view) {
        img = view.findViewById(R.id.img);
        spinner = view.findViewById(R.id.spinner);
        txtSf = view.findViewById(R.id.txt_sf);
        txtSycw = view.findViewById(R.id.txt_sycw);
        btnShuaxin = view.findViewById(R.id.btn_shuaxin);
    }
}
