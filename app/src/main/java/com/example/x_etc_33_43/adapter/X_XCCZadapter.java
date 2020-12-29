package com.example.x_etc_33_43.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.XCCZ;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/13 9:04
 */
public class X_XCCZadapter extends BaseAdapter {
    private List<XCCZ> xcczList;
    private MyXccz myxccz;

    public interface MyXccz{

        void setMyxccz(String plate);
    }

    public void setMyxccz (MyXccz myxccz){
        this.myxccz = myxccz;
    }

    public X_XCCZadapter(List<XCCZ> xcczList) {
        this.xcczList = xcczList;
    }

    @Override
    public int getCount() {
        return xcczList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_xccz, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final XCCZ xccz = xcczList.get(position);
        holder.txtJine.setText(xccz.getBalance());
        holder.txtNumber.setText("第"+xccz.getNumber()+"号小车 剩余金额");
        holder.btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myxccz.setMyxccz(xccz.getPlate());
            }
        });

        return convertView;
    }

    class ViewHolder {
        private TextView txtNumber;
        private TextView txtJine;
        private Button btnChongzhi;

        public ViewHolder(View view) {
            btnChongzhi = view.findViewById(R.id.btn_chongzhi);
            txtNumber = view.findViewById(R.id.txt_number);
            txtJine = view.findViewById(R.id.txt_jine);
        }
    }
}
