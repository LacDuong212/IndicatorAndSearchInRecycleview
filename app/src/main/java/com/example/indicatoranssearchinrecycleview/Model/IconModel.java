package com.example.indicatoranssearchinrecycleview.Model;

import java.io.Serializable;

public class IconModel implements Serializable {
    private Integer imgId;
    private String deccs;

    public IconModel(Integer imgId, String deccs) {
        this.imgId = imgId;
        this.deccs = deccs;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getDeccs() {
        return deccs;
    }

    public void setDeccs(String deccs) {
        this.deccs = deccs;
    }
}
