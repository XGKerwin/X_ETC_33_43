package com.example.x_etc_33_43.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.Color1;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/7 10:03
 */
public class X_DZBC_adapter3 extends BaseAdapter {
    private String[] yang;
    private String[] nong;
    private List<Color1> color1s;
    private Mydzbc3 mydzbc3;

    public interface Mydzbc3{

        void setOn(int position);
    }

    public void setMydzbc3(Mydzbc3 mydzbc3){
        this.mydzbc3 = mydzbc3;
    }

    public X_DZBC_adapter3(String[] yan, String[] nong, List<Color1> colorList) {
        this.nong = nong;
        this.yang = yan;
        this.color1s = colorList;
    }

    @Override
    public int getCount() {
        return yang.length;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dzbc3, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Color1 color1 = color1s.get(position);
        if (position%7==0 || position%7==6){
            holder.yangli.setText(yang[position]);
            holder.nongli.setText(nong[position]);
            holder.lin.setBackgroundResource(R.drawable.hui_bian1);
        }else {
            holder.yangli.setText(yang[position]);
            holder.nongli.setText(nong[position]);
            holder.lin.setBackgroundResource(R.drawable.bai_bian1);
        }

        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yang[position].equals("")){

                }else {
                    int color = color1.getColor();
                    int colo = color+1;
                    color1.setColor(colo);
                    mydzbc3.setOn(position);
                    if (color%2==0){
                        if (position%7==0 || position%7==6){
                            holder.lin.setBackgroundResource(R.drawable.hui_bian1);
                        }else {
                            holder.lin.setBackgroundResource(R.drawable.bai_bian1);
                        }
                    }else {
                        holder.lin.setBackgroundResource(R.drawable.huang_bai1);
                    }

                }
            }
        });




        if (yang[position].equals("")){
            holder.yangli.setText(yang[position]);
            holder.nongli.setText(nong[position]);
            holder.lin.setBackgroundResource(R.drawable.hui_bian1);
        }



        return convertView;
    }

    class ViewHolder {
        private LinearLayout lin;
        private TextView yangli;
        private TextView nongli;

        public ViewHolder(View view) {
            lin = view.findViewById(R.id.lin);
            yangli = view.findViewById(R.id.yangli);
            nongli = view.findViewById(R.id.nongli);
        }
    }

}
