package com.guanqing.github_demo_app;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.Comment;
import com.guanqing.mylibrary.MyService;

import java.util.List;

/**
 * Created by Guanqing on 2016/3/31.
 */
public class CommentsDialog extends DialogFragment {
    public static final String DIALOG_FLAG = "CommentsDialog.DIALOG_FLAG";
    public static final String URL_KEY = "CommentsDialog.URL_KEY";

    private String commentsUrl;
    private TextView textView;

    public static CommentsDialog newInstance(String url) {
        CommentsDialog fragment = new CommentsDialog();
        Bundle bundle = new Bundle();
        bundle.putString(URL_KEY, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get screen size in pixels

        if (getArguments() != null) {
            commentsUrl = getArguments().getString(URL_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_comments, container, false);
        textView = (TextView) view.findViewById(R.id.textview_comments);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        //error check
        if (getDialog() == null || commentsUrl == null){
            return;
        }

        new LoadCommentsTask().execute(commentsUrl);
    }


    /**
     * get all the comments of one issue and display it
     */
    private class LoadCommentsTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            List<Comment> list = MyService.getInstance().getComments(params[0]);
            StringBuilder builder = new StringBuilder();
            for (Comment c : list){
                if (c.getBody()!=null){
                    builder.append(c.getUser().getLogin() + ": \n");
                    builder.append(c.getBody() + "\n");
                    builder.append("- - - - - - - - - - - -\n");
                }

            }
            return builder.toString();
        }

        @Override
        protected void onPostExecute(String comments) {
            if (comments == null || comments.length()==0){
                comments = "No comments so far";
            }
            textView.setText(comments);
        }
    }
}
