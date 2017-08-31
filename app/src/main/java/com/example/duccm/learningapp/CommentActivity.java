package com.example.duccm.learningapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.duccm.learningapp.PostAdapter.POST_VALUES;
import static com.example.duccm.learningapp.PostAdapter.PROFILE_VALUES;

public class CommentActivity extends AppCompatActivity {
    private TextView name;
    private ImageView avatar;
    private ImageView image;
    private TextView status;
    private TextView createdAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initViews();
        setViews();
    }

    public void initViews() {
        name = (TextView) findViewById(R.id.name);
        avatar = (ImageView) findViewById(R.id.avatar);
        image = (ImageView) findViewById(R.id.image);
        status = (TextView) findViewById(R.id.status);
        createdAt = (TextView) findViewById(R.id.created_at);
    }

    public void setViews() {
        Bundle extras = getIntent().getExtras();

        Post post = (Post) extras.getSerializable(POST_VALUES);
        Profile profile = (Profile) extras.getSerializable(PROFILE_VALUES);
        Glide.with(this).load(profile.getAvatar()).into(avatar);
        Glide.with(this).load(post.getImage()).into(image);
        name.setText(profile.getName());
        status.setText(post.getStatus());
        createdAt.setText(post.getCreatedAt());
    }
}
