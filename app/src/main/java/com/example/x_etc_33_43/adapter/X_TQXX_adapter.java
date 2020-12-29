package com.example.x_etc_33_43.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.TQXX;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/6 19:16
 */
public class X_TQXX_adapter extends BaseAdapter {
    private List<TQXX> tqxxList;
    private List<String> strings;

    public X_TQXX_adapter(List<TQXX> tqxxList, List<String> strings) {
        this.tqxxList = tqxxList;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return 5;
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tqxx, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TQXX tqxx = tqxxList.get(position);
        holder.txtRi.setText(strings.get(position));
        switch (tqxx.getWeather()){
            case "晴":
                holder.img.setImageResource(R.drawable.qing);
                holder.line.setBackgroundColor(Color.parseColor("#60B6F9"));
                break;
            case "阴":
                holder.line.setBackgroundColor(Color.parseColor("#F3EDED"));
                holder.img.setImageResource(R.drawable.yin);
                break;
            case "小雨":
                holder.img.setImageResource(R.drawable.xiaoyu);
                holder.line.setBackgroundColor(Color.parseColor("#F1F1F1"));
                break;
        }

        holder.txtQing.setText(tqxx.getWeather());
        holder.txtWendu.setText(tqxx.getInterval().replace("~", "/") + "℃");

        return convertView;
    }


    class ViewHolder {
        private LinearLayout line;
        private TextView txtRi;
        private ImageView img;
        private TextView txtQing;
        private TextView txtWendu;

        public ViewHolder(View view) {
            line = view.findViewById(R.id.line);
            txtRi = view.findViewById(R.id.txt_ri);
            img = view.findViewById(R.id.img);
            txtQing = view.findViewById(R.id.txt_qing);
            txtWendu = view.findViewById(R.id.txt_wendu);
        }
    }
}
