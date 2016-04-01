package com.example;

/**
 * Created by Guanqing on 2016/3/31.
 */
public class User {
    private String login;
    private Integer id;
    private String url;

    /**
     *
     * @param login
     * @param id
     * @param url
     */
    public User(String login, Integer id, String url) {
        if (login != null) {
            this.login = login;
        }
        if (id != null) {
            this.id = id;
        }
        if (url != null) {
            this.url = url;
        }
    }

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
