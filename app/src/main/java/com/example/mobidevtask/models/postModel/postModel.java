package com.example.mobidevtask.models.postModel;

public class postModel {

    private String title;

    public postModel(String title, String body) {
        this.title = title;
        this.body = body;
    }

    private String body;

    public String getTitle() {
        return title;
    }

    public postModel() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
