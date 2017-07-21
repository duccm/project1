package com.example.duccm.learningapp.util;

import android.util.Patterns;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by DucCM on 7/20/2017.
 */

public class Utils {
    public static boolean isValidLoginCredentials(String email, String password) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches() && password.length() > 6;
    }
}
