package com.guanqing.github_demo_app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guanqing on 2016/3/31.
 */
public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<Issue> data;
    private MyViewHolder holder;
    private FragmentManager fm;

    public DashboardAdapter(Context context) {
        mContext = context;
        data = new ArrayList<>();
        fm = ((FragmentActivity) mContext).getSupportFragmentManager();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        holder = (MyViewHolder) viewHolder;
        try{
            bindFeedItem(position, holder);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void bindFeedItem(int position, final MyViewHolder holder) throws Exception {
        Issue issue = data.get(position);
        if (issue.getBody()==null || issue.getBody().length()==0){
            holder.tvTitle.setText("No title");
        }else{
            holder.tvTitle.setText(issue.getTitle());
        }

        if (issue.getBody()==null || issue.getBody().length()==0){
            holder.tvBody.setText("No description");
        }else{
            holder.tvBody.setText(issue.getBody());
        }
        holder.tvUpdate.setText(issue.getUpdatedAt());
        String commentsUrl = issue.getCommentsUrl();
        View.OnClickListener listener = getCommentsListener(commentsUrl);
        holder.view.setOnClickListener(listener);
    }

    private View.OnClickListener getCommentsListener(final String commentsUrl){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentsDialog(commentsUrl);
            }
        };
    }

    private void showCommentsDialog(String url){
        Fragment prev = fm.findFragmentByTag(CommentsDialog.DIALOG_FLAG);
        if (prev != null){
            CommentsDialog df = (CommentsDialog) prev;
            df.dismiss();
            fm.beginTransaction().remove(prev);
        }

        CommentsDialog fragment = CommentsDialog.newInstance(url);
        fragment.show(fm, CommentsDialog.DIALOG_FLAG);
    }


    /**
     * set the data for the adapter
     * @param list
     */
    public void setData(List<Issue> list){
        data = list;
    }


    /**
     * custom view holder
     */
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvTitle;
        TextView tvBody;
        TextView tvUpdate;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            tvTitle = (TextView) view.findViewById(R.id.title);
            tvBody = (TextView) view.findViewById(R.id.body);
            tvUpdate = (TextView) view.findViewById(R.id.updated);
        }
    }
}
