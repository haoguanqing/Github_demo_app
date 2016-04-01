package com.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Issue implements Comparable<Issue>{
    private String url;
    private String repositoryUrl;
    private String commentsUrl;
    private Integer id;
    private Integer number;
    private String title;
    private User user;
    private Integer comments;
    private String createdAt;
    private String updatedAt;
    private String body;

    public Issue(String url,  String commentsUrl, Integer id, Integer number, String title, User user, Integer comments, String createdAt, String updatedAt, String body) {
        if (url != null) this.url = url;
        if (commentsUrl != null) this.commentsUrl = commentsUrl;
        if (id != null) this.id = id;
        if (number != null) this.number = number;
        if (title != null) this.title = title;
        if (user != null) this.user = user;
        if (comments != null) this.comments = comments;
        if (createdAt != null) this.createdAt = createdAt;
        if (updatedAt != null) this.updatedAt = updatedAt;
        if (body != null) this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public Integer getComments() {
        return comments;
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
    public int compareTo(Issue i2) {
        if (getUpdatedDate() == null || i2.getUpdatedDate() == null){
            return 0;
        }
        return 0 - getUpdatedDate().compareTo(i2.getUpdatedDate());
    }
}
