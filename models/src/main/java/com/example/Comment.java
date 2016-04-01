package com.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Guanqing on 2016/3/31.
 */
public class Comment implements Comparable<Comment> {
    private String url;
    private String issueUrl;
    private Integer id;
    private User user;
    private String createdAt;
    private String updatedAt;
    private String body;

    public Comment(String url, String issueUrl, Integer id, User user, String createdAt, String updatedAt, String body) {
        this.url = url;
        this.issueUrl = issueUrl;
        this.id = id;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedDate() {
        try{
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
            return format.parse(createdAt);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public Date getUpdatedDate() {
        try{
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
            return format.parse(updatedAt);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int compareTo(Comment c2) {
        if (getUpdatedDate() == null || c2.getUpdatedDate() == null){
            return 0;
        }
        return 0 - getUpdatedDate().compareTo(c2.getUpdatedDate());
    }
}
