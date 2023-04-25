package com.example.dokobit.Model;

public class File {
    private String name;
    private int Image;

    public File(int image, String name) {
        this.Image = image;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
