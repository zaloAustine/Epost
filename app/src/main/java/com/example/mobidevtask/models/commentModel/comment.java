package com.example.mobidevtask.models.commentModel;

import java.util.List;

public class comment {
    private String message;
    private List<data2> data;

    public comment() {
    }

    public String getMessage() {
        return message;
    }

    public comment(String message, List<data2> data) {
        this.message = message;
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<data2> getData() {
        return data;
    }

    public void setData(List<data2> data) {
        this.data = data;
    }
}
