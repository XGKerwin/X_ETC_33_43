package com.example.x_etc_33_43.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.CLSF;
import com.example.x_etc_33_43.bean.CLSF2;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/12 19:24
 */
public class X_List_adapter extends BaseAdapter {
    private List<CLSF> clsfList;
    private List<CLSF2> clsf2List;

    public X_List_adapter(List<CLSF> clsfList, List<CLSF2> clsf2List) {
        this.clsf2List = clsf2List;
        this.clsfList = clsfList;
    }

    @Override
    public int getCount() {
        if (clsfList.size()==0) return 0;
        return clsfList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_clsf, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CLSF clsf = clsfList.get(position);
        holder.txtKahao.setText(clsf.getId());
        holder.txtCp.setText(clsf.getPlate());
        holder.time1.setText(clsf.getEntrance());
        holder.time2.setText(clsf.getExit());
        holder.txtJine.setText(clsf.getPrice());
        for (int i=0;i<clsf2List.size();i++){
            CLSF2 clsf2 = clsf2List.get(i);
            if (clsf.getNumber().equals(clsf2.getNumber())){
                holder.txtName.setText(clsf2.getOwner());
            }
        }

        return convertView;

    }


    class ViewHolder {
        private TextView txtKahao;
        private TextView txtCp;
        private TextView txtName;
        private TextView time1;
        private TextView time2;
        private TextView txtJine;

        public ViewHolder(View view) {
            txtKahao = view.findViewById(R.id.txt_kahao);
            txtCp = view.findViewById(R.id.txt_cp);
            txtName = view.findViewById(R.id.txt_name);
            time1 = view.findViewById(R.id.time1);
            time2 = view.findViewById(R.id.time2);
            txtJine = view.findViewById(R.id.txt_jine);
        }
    }
}
