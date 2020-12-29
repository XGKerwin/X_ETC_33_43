package com.example.x_etc_33_43.bean;

import java.io.Serializable;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/6 10:00
 */
public class LXXX implements Serializable{

    /**
     *             "id": 1,
     *             "name": "故宫",
     *             "price": "60",
     *             "img": "gugong1",
     *             "presentation": "绝大多数第一次来北京的游客，都会把故宫当作必去之处。故宫又称紫禁城，是明、清两代的皇宫。也是古老中国的标志和象征。虽说这里早已经不是中国的政治中心，但当你置身于气派规模的高墙深院，依然能真真切切感受到它曾经的荣耀。悠久的历史给这里留下了大规模的珍贵建筑和无数文物，也成为今天游玩故宫的主要看点",
     *             "grade": "4",
     *             "tel": "010-88888888"
     */

    private String id,name,price,img,presentation,grade,tel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
