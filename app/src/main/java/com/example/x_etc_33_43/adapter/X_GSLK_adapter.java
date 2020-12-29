package com.example.x_etc_33_43.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_33_43.R;
import com.example.x_etc_33_43.bean.GSLK;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 17:37
 */
public class X_GSLK_adapter extends BaseExpandableListAdapter {
    private List<GSLK> gslkList;

    public X_GSLK_adapter(List<GSLK> gslkList) {
        this.gslkList = gslkList;
    }

    @Override
    public int getGroupCount() {
        return gslkList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return gslkList.get(groupPosition).getSite().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderFU holderFU;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gs_fu, null);
            holderFU = new ViewHolderFU(convertView);
            convertView.setTag(holderFU);
        } else {
            holderFU = (ViewHolderFU) convertView.getTag();
        }
        GSLK gslk = gslkList.get(groupPosition);
        holderFU.g18.setText(gslk.getRoadid());
        holderFU.g18tit.setText(gslk.getRoad());
        holderFU.txtZhuangtai.setText(gslk.getType());
        holderFU.tit.setText(gslk.getRoad());

        if (isExpanded) {
            holderFU.img.setImageResource(R.drawable.xiajiantou);
        } else {
            holderFU.img.setImageResource(R.drawable.zuojiantou);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderZi holderZi;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gs_zi, null);
            holderZi = new ViewHolderZi(convertView);
            convertView.setTag(holderZi);
        } else {
            holderZi = (ViewHolderZi) convertView.getTag();
        }
        holderZi.txtle.setText(gslkList.get(groupPosition).getSite().get(childPosition));


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class ViewHolderFU {
        private TextView g18;
        private TextView g18tit;
        private TextView tit;
        private TextView txtZhuangtai;
        private ImageView img;

        public ViewHolderFU(View view) {
            g18 = view.findViewById(R.id.g18);
            g18tit = view.findViewById(R.id.g18tit);
            tit = view.findViewById(R.id.tit);
            txtZhuangtai = view.findViewById(R.id.txt_zhuangtai);
            img = view.findViewById(R.id.img);
        }
    }

    class ViewHolderZi {
        private TextView txtle;

        public ViewHolderZi(View view) {
            txtle = view.findViewById(R.id.txtle);
        }
    }

}
