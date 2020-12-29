package com.example.x_etc_33_43.bean;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 17:33
 */
public class GSLK {

    /**
     *             "roadid": "G18",
     *             "road": "义乌高速",
     *             "type": "正常",
     *             "site": [
     *                 "刘家窑",
     *                 "磁器口",
     *                 "东单"
     *             ]
     */

    private String roadid,road,type;
    private List<String> site;

    public String getRoadid() {
        return roadid;
    }

    public void setRoadid(String roadid) {
        this.roadid = roadid;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getSite() {
        return site;
    }

    public void setSite(List<String> site) {
        this.site = site;
    }
}
