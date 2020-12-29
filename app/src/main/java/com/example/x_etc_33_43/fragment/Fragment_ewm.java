package com.example.x_etc_33_43.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.activity.Activity_ewm;
import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.adapter.X_spinner_adapter;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/6 20:18
 */
public class Fragment_ewm extends Fragment {
    private Spinner spinner;
    private EditText edJine;
    private EditText edZhouqi;
    private Button btnShengcheng;
    private X_spinner_adapter adapter;
    private String[] arr = {"1","2","3"};
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_ewm, null);
        initView(view);

        title.setText("二维码支付");

        edit();
        spin();
        btn();


        return view;
    }

    private void btn() {
        btnShengcheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jine = edJine.getText().toString();
                String zhouqi = edZhouqi.getText().toString();
                if (jine.equals("")){
                    Toast.makeText(getContext(),"请输入付费金额",Toast.LENGTH_SHORT).show();
                }else if (zhouqi.equals("")){
                    Toast.makeText(getContext(),"请输入刷新周期",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getContext(), Activity_ewm.class)
                            .putExtra("1",jine)
                            .putExtra("2",zhouqi);
                    startActivity(intent);
                }
            }
        });

    }

    private void spin() {
        adapter = new X_spinner_adapter(arr);
        spinner.setAdapter(adapter);
    }

    private void edit() {
        edJine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(0)){
                    edJine.setText("");
                    Toast.makeText(getContext(),"不能输入0",Toast.LENGTH_SHORT).show();
                }
            }
        });
        edZhouqi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")){
                    edZhouqi.setText("");
                    Toast.makeText(getContext(),"不能输入0",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        spinner = view.findViewById(R.id.spinner);
        edJine = view.findViewById(R.id.ed_jine);
        edZhouqi = view.findViewById(R.id.ed_zhouqi);
        btnShengcheng = view.findViewById(R.id.btn_shengcheng);
    }
}
