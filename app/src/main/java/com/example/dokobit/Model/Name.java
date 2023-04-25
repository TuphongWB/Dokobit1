package com.example.dokobit.Model;

import java.io.Serializable;

public class Name implements Serializable {
    private int resourceId;
    private String logo;

    public Name(int resourceId, String logo) {
        this.resourceId = resourceId;
        this.logo = logo;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
