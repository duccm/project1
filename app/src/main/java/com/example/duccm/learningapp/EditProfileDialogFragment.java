package com.example.duccm.learningapp;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duccm.learningapp.util.Utils;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final int REQUEST_IMAGE_CAPTURE = 100;

    private Profile currentUser;
    private ImageView avatar;
    private TextView editAvatar;
    private EditText name;
    private EditText email;
    private EditText phone;
    private EditText birthday;
    private Button update;
    private TextView cancel;
    private OnSubmitButtonClickedListener mCallback;
    private String currentAvatarPath;

    public EditProfileDialogFragment() {
        // Required empty public constructor
    }

    public interface OnSubmitButtonClickedListener {
        public void passProfileData(Profile profile);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnSubmitButtonClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnSubmitButtonClickedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_profile_dialog, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
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
        editAvatar = (TextView) v.findViewById(R.id.edit_avatar);
        name = (EditText) v.findViewById(R.id.edit_name);
        email = (EditText) v.findViewById(R.id.edit_email);
        phone = (EditText) v.findViewById(R.id.edit_phone);
        birthday = (EditText) v.findViewById(R.id.edit_birthday);
        update = (Button) v.findViewById(R.id.button_update);
        cancel = (TextView) v.findViewById(R.id.text_cancel);
    }

    public void setViews() {
        currentUser = new ProfileList().getProfileList().get(0);
        Glide.with(this).load(currentUser.getAvatar()).into(avatar);
        name.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        phone.setText(currentUser.getPhone());
        birthday.setText(currentUser.getBirthday());
    }

    public void initListeners() {
        editAvatar.setOnClickListener(this);
        update.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_avatar:
                captureAvatar();
                break;
            case R.id.button_update:
                if (currentAvatarPath != null) {
                    currentUser.setAvatar(currentAvatarPath);
                }
                currentUser.setName(name.getText().toString().trim());
                currentUser.setEmail(email.getText().toString().trim());
                currentUser.setPhone(phone.getText().toString().trim());
                currentUser.setBirthday(birthday.getText().toString().trim());
                mCallback.passProfileData(currentUser);
                break;
            case R.id.text_cancel:
                Fragment dialogFragment = getActivity().getSupportFragmentManager().findFragmentByTag("edit_profile_dialog");
                if (dialogFragment != null && dialogFragment instanceof EditProfileDialogFragment) {
                    EditProfileDialogFragment dialog = (EditProfileDialogFragment) dialogFragment;
                    dialog.dismiss();
                }
                break;
        }
    }

    public void captureAvatar() {
        if (getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = Utils.createImageFile(getContext());
                    currentAvatarPath = photoFile.getAbsolutePath();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.e("IO", String.valueOf(ex));
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(getContext(),
                            "com.example.duccm.learningapp.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        } else {
            Toast.makeText(getContext(), "Device not support camera", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(currentAvatarPath);
            avatar.setImageBitmap(imageBitmap);
        }
    }
}
