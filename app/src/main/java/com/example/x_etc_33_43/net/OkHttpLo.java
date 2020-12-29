package com.example.x_etc_33_43.net;

import org.json.JSONObject;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 17:09
 */
public interface OkHttpLo {
    void onResponse(JSONObject jsonObject);

    void onFailure(IOException obj);
}
