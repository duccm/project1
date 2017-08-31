package com.example.duccm.learningapp;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duccm.learningapp.util.DateTimeUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostStatusDialogFragment extends DialogFragment implements View.OnClickListener {
    private Profile currentUser;
    private Post post;
    private ImageView avatar;
    private TextView name;
    private EditText status;
    private TextView tvCancel;
    private TextView tvPost;
    private OnPostButtonClickedListener callback;

    public PostStatusDialogFragment() {
        // Required empty public constructor
    }

    public interface OnPostButtonClickedListener {
        public void passPostData(Post post);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnPostButtonClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnPostButtonClickedListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post_status_dialog, container, false);
        initViews(v);
        setViews();
        initListeners();
        return v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void initViews(View v) {
        avatar = (ImageView) v.findViewById(R.id.avatar);
        name = (TextView) v.findViewById(R.id.name);
        status = (EditText) v.findViewById(R.id.status);
        tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
        tvPost = (TextView) v.findViewById(R.id.tv_post);
    }

    public void setViews() {
        currentUser = new ProfileList().getProfileList().get(0);
        Glide.with(this).load(currentUser.getAvatar()).into(avatar);
        name.setText(currentUser.getName());
    }

    public void initListeners() {
        tvCancel.setOnClickListener(this);
        tvPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                Fragment dialogFragment = getActivity().getSupportFragmentManager().findFragmentByTag("post_status_dialog");
                if (dialogFragment != null && dialogFragment instanceof PostStatusDialogFragment) {
                    PostStatusDialogFragment dialog = (PostStatusDialogFragment) dialogFragment;
                    dialog.dismiss();
                }
                break;
            case R.id.tv_post:
                String createdAt = DateTimeUtils.getFormatPostDateTime();
                post = new Post(new UserPost(0), status.getText().toString(), "", createdAt);
                callback.passPostData(post);
                break;
        }
    }
}
