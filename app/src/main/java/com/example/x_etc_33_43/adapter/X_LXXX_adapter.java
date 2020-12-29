package com.example.x_etc_33_43.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_33_43.bean.LXXX;
import com.example.x_etc_33_43.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/6 10:02
 */
public class X_LXXX_adapter extends BaseAdapter {
    private List<LXXX> lxxxList;

    public X_LXXX_adapter(List<LXXX> lxxxList) {
        this.lxxxList = lxxxList;
    }

    @Override
    public int getCount() {
        return lxxxList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_lxxx, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        LXXX lxxx = lxxxList.get(position);
        holder.txtTitle.setText(lxxx.getName());
        holder.txtPiaojia.setText("票价："+lxxx.getPrice());
        switch (lxxx.getImg()){
            case "gugong1":
                holder.img.setImageResource(R.drawable.gugong1);
                break;
            case "gugong2":
                holder.img.setImageResource(R.drawable.gugong2);
                break;
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView img;
        private TextView txtTitle;
        private TextView txtPiaojia;

        public ViewHolder(View view) {
            img = view.findViewById(R.id.img);
            txtTitle = view.findViewById(R.id.txt_title);
            txtPiaojia = view.findViewById(R.id.txt_piaojia);
        }
    }
}
