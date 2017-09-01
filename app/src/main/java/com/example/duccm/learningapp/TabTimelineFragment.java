package com.example.duccm.learningapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.duccm.learningapp.PostAdapter.POSTS_PER_PAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabTimelineFragment extends Fragment {
    public List<Post> postList;
    public List<Post> visiblePostList;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private int totalItems;
    private int nextLoadPage;
    private int numPages;

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
            getAsyncData(new OnResponseListener() {
                @Override
                public void onSuccess(List<Post> response) {
                    postList = response;
                    totalItems = postList.size();
                    nextLoadPage = 0;
                    numPages = (int) Math.ceil((double) totalItems / POSTS_PER_PAGE);
                    visiblePostList = new ArrayList<Post>(postList.subList(nextLoadPage * POSTS_PER_PAGE, (nextLoadPage += 1) * POSTS_PER_PAGE));
                    postAdapter = new PostAdapter(recyclerView, visiblePostList);
                    recyclerView.setAdapter(postAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    loadMoreListener();
                }

                @Override
                public void onError(String text) {
                    Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return v;
    }

    public void addStatus(Post post) {
        postList.add(0, post);
    }

    public interface OnResponseListener {
        void onSuccess(List<Post> response);
        void onError(String text);
    }

    public void getAsyncData(final OnResponseListener callback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Post>> call = apiService.getTimeline();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onSuccess(response.body());
                    }
                } else {
                    if (callback != null) {
                        callback.onError(response.code() + ":" + getResources().getString(R.string.get_timeline_failed));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                if (callback != null) {
                    callback.onError(t.toString());
                }
            }
        });
    }

    public void loadMoreListener() {
        postAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        visiblePostList.add(null);
                        postAdapter.notifyItemInserted(visiblePostList.size() - 1);

                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                        Call<List<Post>> call = apiService.getTimeline();
                        call.enqueue(new Callback<List<Post>>() {
                            @Override
                            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                                if(response.isSuccessful()) {
                                    visiblePostList.remove(visiblePostList.size() - 1);
                                    postAdapter.notifyItemRemoved(visiblePostList.size());
                                    postList = response.body();
                                    if (nextLoadPage < numPages - 1) {
                                        visiblePostList.addAll(postList.subList(nextLoadPage * POSTS_PER_PAGE, (nextLoadPage += 1) * POSTS_PER_PAGE));
                                    } else if (nextLoadPage == numPages - 1) {
                                        visiblePostList.addAll(postList.subList(nextLoadPage * POSTS_PER_PAGE, totalItems));
                                        nextLoadPage += 1;
                                    }
                                    postAdapter.notifyDataSetChanged();
                                    postAdapter.setLoaded();
                                } else {
                                    Toast.makeText(getContext(), response.code() + ":" + getResources().getString(R.string.get_timeline_failed), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Post>> call, Throwable t) {
                                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
}
