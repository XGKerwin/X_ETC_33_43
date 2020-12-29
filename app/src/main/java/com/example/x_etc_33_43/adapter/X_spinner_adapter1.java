package com.example.x_etc_33_43.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/7 11:25
 */
public class X_spinner_adapter1 extends BaseAdapter {
    private List<String> didian;


    public X_spinner_adapter1(List<String> didian) {
        this.didian = didian;
    }

    @Override
    public int getCount() {
        return didian.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String s = didian.get(position);
        View view = View.inflate(parent.getContext(),android.R.layout.simple_list_item_1,null);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(s);
        textView.setTextSize(25);
        textView.setTextColor(Color.BLACK);

        return view;
    }
}
