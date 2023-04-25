package com.example.dokobit.Model;

public class Country {
    private int image;
    private String country;

    public Country(int image, String country) {
        this.image = image;
        this.country = country;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
