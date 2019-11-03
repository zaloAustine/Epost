package com.example.mobidevtask.models.commentModel;

public class data2 {
    private int id;
    private int post_id;
    private String body;
    private int  like;
    private String created_at;
    private String  updated_at;

    public data2(int like) {
        this.like = like;
    }

    public data2() {
    }

    public data2(String body) {
        this.body = body;
    }

    public data2(int id, int post_id, String body, int like, String created_at, String updated_at) {
        this.id = id;
        this.post_id = post_id;
        this.body = body;
        this.like = like;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
