package com.example.x_etc_33_43.bean;

import org.litepal.crud.LitePalSupport;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/5 20:55
 */
public class CZJL extends LitePalSupport {

    private String number;
    private String cph;
    private String jine;
    private String time;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
