package com.example.duccm.learningapp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by duccm on 8/16/17.
 */

public class DateTimeUtils {
    private static final String FORMAT_POST_DATE_TIME = "HH:mm, dd/MM/yyyy";

    public static String getFormatPostDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_POST_DATE_TIME);
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
