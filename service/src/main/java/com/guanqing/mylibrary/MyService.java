package com.guanqing.mylibrary;

import android.util.Log;

import com.example.Comment;
import com.example.Issue;
import com.example.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Guanqing on 2016/3/31.
 */
public class MyService {
    static MyService mInstance;

    private MyService(){}

    public synchronized static MyService getInstance(){
        if(mInstance == null){
            mInstance = new MyService();
            Log.d("HGQ", "New Instance");
        }
        return mInstance;
    }

    public List<Issue> getIssues(String url){
        String s = readFromUrl(url);
        return convertToIssue(s);
    }

    public List<Comment> getComments(String url){
        return convertToComment(readFromUrl(url));
    }

    private List<Issue> convertToIssue(String json){
        List<Issue> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            Log.d("HGQ", "array length: " + array.length());
            for (int i = 0; i < array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                JSONObject u = obj.getJSONObject("user");
                User user = new User(
                        u.optString("login"),
                        u.getInt("id"),
                        u.optString("url"));
                Issue issue = new Issue(
                        obj.optString("url"),
                        obj.optString("comments_url"),
                        obj.optInt("id"),
                        obj.optInt("number"),
                        obj.optString("title"),
                        user,
                        obj.optInt("comments"),
                        obj.optString("created_at"),
                        obj.optString("updated_at"),
                        obj.optString("body")
                );
                list.add(issue);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Collections.sort(list);
        return list;
    }

    private List<Comment> convertToComment(String json){
        List<Comment> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                JSONObject u = obj.getJSONObject("user");
                User user = new User(
                        u.optString("login"),
                        u.optInt("id"),
                        u.optString("url"));
                Comment comm = new Comment(
                        obj.optString("url"),
                        obj.optString("issue_url"),
                        obj.optInt("id"),
                        user,
                        obj.optString("created_at"),
                        obj.optString("updated_at"),
                        obj.optString("body")
                );
                list.add(comm);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Collections.sort(list);
        return list;
    }

    private String readFromUrl(String s){
        StringBuffer res = new StringBuffer();
        try {
            URL url = new URL(s);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            url.openStream()));
            String line;
            while ((line = in.readLine()) != null){
                res.append(line);
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("HGQ", res.toString());
        return res.toString();
    }
}
