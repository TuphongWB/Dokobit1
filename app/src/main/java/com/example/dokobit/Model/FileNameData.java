package com.example.dokobit.Model;

public class FileNameData {
    private static FileNameData instance;
    private String fileName;

    private FileNameData() {
    }

    public static FileNameData getInstance() {
        if (instance == null) {
            instance = new FileNameData();
        }
        return instance;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
