package com.example.dokobit.Model;

public class DocumentModel {
    String documentName;
    int imgDoc;

    public DocumentModel(String documentName, int imgDoc) {
        this.documentName = documentName;
        this.imgDoc = imgDoc;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public int getImgDoc() {
        return imgDoc;
    }

    public void setImgDoc(int imgDoc) {
        this.imgDoc = imgDoc;
    }
}
