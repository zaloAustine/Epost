package com.example.mobidevtask.models.postModel;

import java.util.List;

import androidx.room.Entity;

@Entity()
public class Message {
    //https://rest-api.mobidev-sandbox.com/api/posts

    private String message;
    private List<com.example.mobidevtask.models.postModel.data> data;

    public Message(String message, List<com.example.mobidevtask.models.postModel.data> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<com.example.mobidevtask.models.postModel.data> getData() {
        return data;
    }



    public void setData(List<com.example.mobidevtask.models.postModel.data> data) {
        this.data = data;
    }
    public void test(){
        //branch test
    }
}
