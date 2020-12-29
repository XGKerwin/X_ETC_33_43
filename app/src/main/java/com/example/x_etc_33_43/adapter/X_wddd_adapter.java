package com.example.x_etc_33_43.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.DZBCdingdan;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/7 12:16
 */
public class X_wddd_adapter extends BaseAdapter {
    private List<DZBCdingdan> dzbCdingdanList;

    public X_wddd_adapter(List<DZBCdingdan> dzbCdingdanList) {
        this.dzbCdingdanList = dzbCdingdanList;
    }

    @Override
    public int getCount() {
        if (dzbCdingdanList.size() == 0)
            return 0;
        return dzbCdingdanList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wddd, null);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DZBCdingdan dzbCdingdan = dzbCdingdanList.get(position);
        holder.name.setText("乘车人："+dzbCdingdan.getName());
        holder.tel.setText("手机号码："+dzbCdingdan.getTel());
        holder.shangchedidian.setText("上车地点："+dzbCdingdan.getShangche());
        holder.riqi.setText("乘车日期："+dzbCdingdan.getRiqi());
        return convertView;
    }


    class ViewHolder {
        private TextView name;
        private TextView luxian;
        private TextView tel;
        private TextView shangchedidian;
        private TextView riqi;

        public ViewHolder(View view) {
            name = view.findViewById(R.id.name);
            luxian = view.findViewById(R.id.luxian);
            tel = view.findViewById(R.id.tel);
            shangchedidian = view.findViewById(R.id.shangchedidian);
            riqi = view.findViewById(R.id.riqi);
        }
    }
}
