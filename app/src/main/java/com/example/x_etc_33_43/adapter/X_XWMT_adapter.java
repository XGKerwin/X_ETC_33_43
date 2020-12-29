package com.example.x_etc_33_43.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.XWMT;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/7 16:18
 */
public class X_XWMT_adapter extends BaseAdapter {
    private List<XWMT> xwmtList;

    public X_XWMT_adapter(List<XWMT> xwmtList) {
        this.xwmtList = xwmtList;
    }

    @Override
    public int getCount() {
        if (xwmtList.size() == 0) return 0;
        return xwmtList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_xwmt, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        XWMT xwmt = xwmtList.get(position);
        holder.txtMsg.setText(xwmt.getNO());

        return convertView;
    }


    class ViewHolder {
        private TextView txtMsg;

        public ViewHolder(View view) {
            txtMsg = view.findViewById(R.id.txt_msg);
        }
    }

}
