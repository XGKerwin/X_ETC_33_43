package com.example.x_etc_33_43.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_33_43.activity.Activity_ic;
import com.example.x_etc_33_43.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/7 17:39
 */

public class Fragment_ICcz extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_iccz,null);

        Intent intent = new Intent(getContext(), Activity_ic.class);
        startActivity(intent);


        return view;
    }


}
