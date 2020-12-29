package com.example.x_etc_33_43.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.DZBC;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/7 8:26
 */
public class X_DZBC_adapter extends BaseAdapter {
    private List<DZBC> dzbcList;

    public X_DZBC_adapter(List<DZBC> dzbcList) {
        this.dzbcList = dzbcList;
    }

    @Override
    public int getCount() {
        return dzbcList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dzbc1, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DZBC dzbc = dzbcList.get(position);
        holder.txtLu.setText(dzbc.getId() + "路");
        List<String> strings = dzbc.getBusline();
        holder.txtTit.setText(strings.get(0) + "-----" + strings.get(strings.size() - 1));
        holder.txtPiaojia.setText("票价： ￥" + dzbc.getFares() + "   里程：" + dzbc.getMileage() + ".0 km");
        holder.txtTime1.setText(dzbc.getTime().split("~")[0]);
        holder.txtTime2.setText(dzbc.getTime().split("~")[1]);

        return convertView;
    }

    class ViewHolder {
        private TextView txtLu;
        private TextView txtTit;
        private TextView txtPiaojia;
        private TextView txtTime1;
        private TextView txtTime2;
        private ImageView imgJiantou;

        public ViewHolder(View view) {
            txtLu = view.findViewById(R.id.txt_lu);
            txtTit = view.findViewById(R.id.txt_tit);
            txtPiaojia = view.findViewById(R.id.txt_piaojia);
            txtTime1 = view.findViewById(R.id.txt_time1);
            txtTime2 = view.findViewById(R.id.txt_time2);
            imgJiantou = view.findViewById(R.id.img_jiantou);
        }
    }
}
