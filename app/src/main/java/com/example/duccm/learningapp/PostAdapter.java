package com.example.duccm.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by DucCM on 7/25/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Post> postList;
    public List<Profile> profileList;
    private LayoutInflater inflater;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;

    public static final String POST_VALUES = "post_values";
    public static final String PROFILE_VALUES = "profile_values";
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;
    public static final int POSTS_PER_PAGE = 4;

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView avatar;
        public TextView name;
        public ImageView image;
        public TextView status;
        public TextView createdAt;
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
            createdAt = (TextView) itemView.findViewById(R.id.created_at);
            postComment = (LinearLayout) itemView.findViewById(R.id.post_comment);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.post_comment:
                    Post post = postList.get(getAdapterPosition());
                    Profile profile = profileList.get(post.getUserPost().getId());
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

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.pb_loading);
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public PostAdapter(RecyclerView recyclerView, List<Post> postList) {
        this.postList = postList;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount == lastVisibleItem + 1) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        if (viewType == VIEW_TYPE_ITEM) {
            View itemView = inflater.inflate(R.layout.item_timeline, parent, false);
            return new PostViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View itemView = inflater.inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PostViewHolder) {
            PostViewHolder postViewHolder = (PostViewHolder) holder;

            profileList = new ProfileList().getProfileList();

            Post post = postList.get(position);

            Glide.with(holder.itemView.getContext()).load(profileList.get(post.getUserPost().getId()).getAvatar()).into(postViewHolder.avatar);
            Glide.with(holder.itemView.getContext()).load(post.getImage()).into(postViewHolder.image);

            postViewHolder.name.setText(profileList.get(post.getUserPost().getId()).getName());
            postViewHolder.status.setText(post.getStatus());
            postViewHolder.createdAt.setText(post.getCreatedAt());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return postList == null || postList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
}
