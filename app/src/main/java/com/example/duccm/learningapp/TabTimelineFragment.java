package com.example.duccm.learningapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabTimelineFragment extends Fragment {
    public List<Post> postList;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private Post post;

    public TabTimelineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_timeline, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_timeline);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (postList == null) {
            postList = new PostList().getPostList();
        }
        postAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(postAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return v;
    }

    public void addStatus(Post post) {
        postList.add(0, post);
    }
}
