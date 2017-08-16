package com.example.duccm.learningapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duccm on 8/9/17.
 */

public class ProfileList {
    private List<Profile> profileList;

    public List<Profile> getProfileList() {
        return profileList;
    }

    public ProfileList() {
        profileList = new ArrayList<>();
        Profile profile = new Profile(0, "http://www.yhail.net/images/default-user-icon-profile.png", "AAAA", "a@a.a", "12121212", "12/12/12");
        profileList.add(profile);

        profile = new Profile(1, "", "BBBB", "b@b.b", "34343434", "34/34/34");
        profileList.add(profile);

        profile = new Profile(2, "", "CCCC", "c@c.c", "56565656", "56/56/56");
        profileList.add(profile);

        profile = new Profile(3, "", "DDDD", "d@d.d", "78787878", "78/78/78");
        profileList.add(profile);

        profile = new Profile(4, "", "EEEE", "e@e.e", "90909090", "90/90/90");
        profileList.add(profile);
    }
}
