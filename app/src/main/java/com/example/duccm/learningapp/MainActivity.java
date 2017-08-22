package com.example.duccm.learningapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static com.example.duccm.learningapp.PostAdapter.POST_VALUES;

/**
 * Created by DucCM on 7/19/2017.
 */

public class MainActivity extends AppCompatActivity implements PostStatusDialogFragment.OnPostButtonClickedListener, EditProfileDialogFragment.OnSubmitButtonClickedListener {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int tabSelected;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.timeline);

        adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
                getSupportActionBar().setTitle(adapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        tabSelected = viewPager.getCurrentItem();
        switch (tabSelected) {
            case 0:
                break;
            case 1:
                menu.clear();
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_post:
                showDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDialog() {
        PostStatusDialogFragment dialog = new PostStatusDialogFragment();
        dialog.show(getSupportFragmentManager(), "post_status_dialog");

    }

    @Override
    public void passPostData(Post post) {
        FragmentManager fm = this.getSupportFragmentManager();
        Fragment dialogFragment = fm.findFragmentByTag("post_status_dialog");
        if (dialogFragment !=null && dialogFragment instanceof PostStatusDialogFragment) {
            PostStatusDialogFragment dialog = (PostStatusDialogFragment) dialogFragment;
            dialog.dismiss();
        }
        TabTimelineFragment timelineFragment =
                (TabTimelineFragment) adapter.getRegisteredFragment(viewPager.getCurrentItem());
        Bundle bundle = new Bundle();
        bundle.putSerializable(POST_VALUES, post);
        timelineFragment.addStatus(post);
        FragmentTransaction ft = fm.beginTransaction();
        ft.detach(timelineFragment);
        ft.attach(timelineFragment);
        ft.commit();
    }

    @Override
    public void passProfileData(Profile profile) {
        FragmentManager fm = this.getSupportFragmentManager();
        Fragment dialogFragment = fm.findFragmentByTag("edit_profile_dialog");
        if (dialogFragment !=null && dialogFragment instanceof EditProfileDialogFragment) {
            EditProfileDialogFragment dialog = (EditProfileDialogFragment) dialogFragment;
            dialog.dismiss();
        }
        TabProfileFragment profileFragment =
                (TabProfileFragment) adapter.getRegisteredFragment(viewPager.getCurrentItem());
        profileFragment.setProfile(profile);
        FragmentTransaction ft = fm.beginTransaction();
        ft.detach(profileFragment);
        ft.attach(profileFragment);
        ft.commit();
    }
}
