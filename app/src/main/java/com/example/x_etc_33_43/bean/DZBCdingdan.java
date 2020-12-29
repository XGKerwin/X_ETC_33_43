package com.example.x_etc_33_43.bean;

import org.litepal.crud.LitePalSupport;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/7 11:57
 */
public class DZBCdingdan extends LitePalSupport {

    private String name,tel,shangche,riqi,luxian;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getShangche() {
        return shangche;
    }

    public void setShangche(String shangche) {
        this.shangche = shangche;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }

    public String getLuxian() {
        return luxian;
    }

    public void setLuxian(String luxian) {
        this.luxian = luxian;
    }
}
