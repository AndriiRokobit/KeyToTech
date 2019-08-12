package com.test.keytotech.mvp.comments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.keytotech.R;
import com.test.keytotech.model.CommentsResponse;
import com.test.keytotech.model.ParcelableComments;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentHolder> {

    private List<ParcelableComments> commentsResponses = Collections.emptyList();
    private Context context;

    public void setData(List<ParcelableComments> commentsResponses, Context context){
        this.commentsResponses = commentsResponses;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.bindView(commentsResponses, position);
    }

    @Override
    public int getItemCount() {
        return commentsResponses.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.email)
        TextView email;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(List<ParcelableComments> list, int position){
            String commentName = "Name:" + list.get(position).getName();
            String commentEmail = "Email:" + list.get(position).getEmail();
            name.setText(commentName);
            email.setText(commentEmail);
        }
    }
}
