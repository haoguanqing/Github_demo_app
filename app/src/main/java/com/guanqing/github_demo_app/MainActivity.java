package com.guanqing.github_demo_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.Issue;
import com.guanqing.mylibrary.MyService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DashboardAdapter adapter;

    private String RAILS_REPO_URL = "https://api.github.com/repos/rails/rails/issues";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new DashboardAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        //check network connection
        if (!Utils.isInternetConnected(this)){
            Toast.makeText(this, "No Network", Toast.LENGTH_SHORT).show();
            return;
        }
        new loadIssuesTask().execute(RAILS_REPO_URL);
    }


    private class loadIssuesTask extends AsyncTask<String, Void, List<Issue>>{
        @Override
        protected List<Issue> doInBackground(String... params) {
            List<Issue> list = MyService.getInstance().getIssues(params[0]);
            return list;
        }

        @Override
        protected void onPostExecute(List<Issue> list) {
            adapter.setData(list);
            adapter.notifyDataSetChanged();
        }
    }
}
