package com.example.x_etc_33_43.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.XCCZ;
import com.example.x_etc_33_43.adapter.X_XCCZadapter;
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
 * date   : 2020/12/13 8:49
 */
public class Fragment_xccz extends Fragment {

    private ListView listview;
    private List<XCCZ> xcczList;
    private X_XCCZadapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_xccz, null);
        initView(view);
        
        
        getOkHttp();


        return view;
    }

    private void getOkHttp() {
        if (xcczList == null){
            xcczList = new ArrayList<>();
        }else {
            xcczList.clear();
        }
        new OkHttpTo()
                .setUrl("get_vehicle")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        xcczList.addAll((Collection<? extends XCCZ>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<XCCZ>>(){}.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        if (adapter == null){
            adapter = new X_XCCZadapter(xcczList);
            listview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
        adapter.setMyxccz(new X_XCCZadapter.MyXccz() {
            @Override
            public void setMyxccz(final String plate) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dia_xccz,null);
                builder.setView(view);

                TextView cph = view.findViewById(R.id.txt_cph);
                final EditText ed_jine = view.findViewById(R.id.ed_jine);
                Button btn_quxiao = view.findViewById(R.id.btn_quxiao);
                Button btn_chongzhi = view.findViewById(R.id.btn_chongzhi);

                cph.setText("车牌号："+plate);

                ed_jine.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().equals("0")){
                            ed_jine.setText("");
                            Toast.makeText(getContext(),"请输入1-1000之内的数",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                final AlertDialog dialog = builder.show();
                btn_chongzhi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String jine = ed_jine.getText().toString();
                        if (jine.equals("")){
                            Toast.makeText(getContext(),"请输入充值金额",Toast.LENGTH_SHORT).show();
                        }else {
                            setOkhttp(jine,plate,dialog,ed_jine);
                        }
                    }
                });

                btn_quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    private void setOkhttp(String jine, String plate, final AlertDialog dialog, final EditText ed_jine) {
        //{"UserName":"user1","plate":"鲁A10001","balance":"100"}
        new OkHttpTo()
                .setUrl("set_balance")
                .setJsonObject("UserName","user1")
                .setJsonObject("plate",plate)
                .setJsonObject("balance",jine)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        dialog.dismiss();
                        ed_jine.setText("");
                        Toast.makeText(getContext(),"充值成功",Toast.LENGTH_SHORT).show();
                        getOkHttp();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        listview = view.findViewById(R.id.listview);
    }
}
