package com.example.x_etc_33_43.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_33_43.bean.CZJL;
import com.example.x_etc_33_43.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/6 9:39
 */
public class X_Listview_adapter extends BaseAdapter {
    private List<CZJL> czjlList;

    public X_Listview_adapter(List<CZJL> czjlList) {
        this.czjlList = czjlList;
    }

    @Override
    public int getCount() {
        if (czjlList.size() == 0) return 0;
        return czjlList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_czjl, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CZJL czjl = czjlList.get(position);
        holder.number.setText(czjl.getNumber());
        holder.cph.setText(czjl.getCph());
        holder.jine.setText(czjl.getJine());
        holder.time.setText(czjl.getTime());


        return convertView;
    }

    class ViewHolder {
        private TextView number;
        private TextView cph;
        private TextView jine;
        private TextView time;

        public ViewHolder(View view) {
            number = view.findViewById(R.id.number);
            cph = view.findViewById(R.id.cph);
            jine = view.findViewById(R.id.jine);
            time = view.findViewById(R.id.time);
        }
    }
}
