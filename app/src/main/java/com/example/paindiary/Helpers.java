package com.example.paindiary;

import android.text.TextUtils;
import android.util.Patterns;

public class Helpers {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
