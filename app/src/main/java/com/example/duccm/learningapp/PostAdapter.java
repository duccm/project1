package com.example.duccm.learningapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DucCM on 7/25/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> postList;
    private LayoutInflater inflater;

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatar;
        public TextView name;
        public ImageView image;
        public TextView status;
        public TextView time;

        public PostViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
            status = (TextView) itemView.findViewById(R.id.status);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View itemView = inflater.inflate(R.layout.item_timeline, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.avatar.setBackgroundResource(R.drawable.ic_profile);
        holder.name.setText("No Name");
        holder.image.setBackgroundResource(R.drawable.image);
        holder.status.setText(post.getStatus());
        holder.time.setText(post.getTime());
    }

    @Override
    public int getItemCount() {
        return postList == null? 0 : postList.size();
    }
}
