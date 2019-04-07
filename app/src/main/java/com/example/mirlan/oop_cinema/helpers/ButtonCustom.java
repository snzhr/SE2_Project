package com.example.mirlan.oop_cinema.helpers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import com.example.acer.oopproject.R;

/**
 *
 */

public class ButtonCustom extends AppCompatButton {

    private static final String TAG = "ButtonCustom";

    public ButtonCustom(Context context) {
        super(context);
    }

    public ButtonCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public ButtonCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonCustom);
        String customFont = ta.getString(R.styleable.ButtonCustom_buttonFont);
        setCustomFont(context, customFont);
        ta.recycle();
    }

    private void setCustomFont(Context context, String customFont) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + customFont);
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: " + e.getMessage());
            return;
        }
        setTypeface(tf);
    }
}
