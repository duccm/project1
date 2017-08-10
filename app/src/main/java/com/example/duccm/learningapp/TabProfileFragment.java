package com.example.duccm.learningapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabProfileFragment extends Fragment {
    private Profile currentUser;
    private ImageView avatar;
    private TextView name;
    private TextView email;
    private TextView phone;
    private TextView birthday;

    public TabProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_profile, container, false);
        initViews(v);
        setViews();
        return v;
    }

    public void initViews(View v) {
        avatar = (ImageView) v.findViewById(R.id.profile_avatar);
        name = (TextView) v.findViewById(R.id.profile_name);
        email = (TextView) v.findViewById(R.id.profile_email);
        phone = (TextView) v.findViewById(R.id.profile_phone);
        birthday = (TextView) v.findViewById(R.id.profile_birthday);
    }

    public void setViews() {
        currentUser = new ProfileList().getProfileList().get(0);
        Glide.with(this).load(currentUser.getAvatar()).into(avatar);
        name.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        phone.setText(currentUser.getPhone());
        birthday.setText(currentUser.getBirthday());
    }
}
