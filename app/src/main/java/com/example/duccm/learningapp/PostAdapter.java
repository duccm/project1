package com.example.duccm.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by DucCM on 7/25/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> postList;
    public List<Profile> profileList;
    private LayoutInflater inflater;

    public static final String POST_VALUES = "post_values";
    public static final String PROFILE_VALUES = "profile_values";

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView avatar;
        public TextView name;
        public ImageView image;
        public TextView status;
        public TextView time;
        public LinearLayout postComment;

        public PostViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
            postComment.setOnClickListener(this);
        }

        public void initViews(View v) {
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
            status = (TextView) itemView.findViewById(R.id.status);
            time = (TextView) itemView.findViewById(R.id.time);
            postComment = (LinearLayout) itemView.findViewById(R.id.post_comment);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.post_comment:
                    Post post = postList.get(getAdapterPosition());
                    Profile profile = profileList.get(post.getUser_id());
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Intent intent = new Intent(activity, CommentActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(POST_VALUES, post);
                    bundle.putSerializable(PROFILE_VALUES, profile);
                    intent.putExtras(bundle);
                    activity.startActivity(intent);
                    break;
            }
        }
    }

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View itemView = inflater.inflate(R.layout.item_timeline, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        profileList = new ProfileList().getProfileList();

        Post post = postList.get(position);

        Glide.with(holder.itemView.getContext()).load(profileList.get(post.getUser_id()).getAvatar()).into(holder.avatar);
        Glide.with(holder.itemView.getContext()).load(post.getImage()).into(holder.image);

        holder.name.setText(profileList.get(post.getUser_id()).getName());
        holder.status.setText(post.getStatus());
        holder.time.setText(post.getTime());
    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }

}
