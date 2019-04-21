package com.example.mirlan.oop_cinema.helpers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Log;

import com.example.acer.oopproject.R;

/**
 * 
 */

public class TextInputLayoutCustom extends TextInputLayout {

    private static final String TAG = "TextInputLayoutCustom";

    public TextInputLayoutCustom(Context context) {
        super(context);
    }

    public TextInputLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public TextInputLayoutCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextInputLayoutCustom);
        String font = ta.getString(R.styleable.TextInputLayoutCustom_tilFont);
        setCustomFont(context, font);
        ta.recycle();
    }

    private void setCustomFont(Context context, String font) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + font);
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: " + e.getMessage());
            return;
        }
        setTypeface(tf);
    }
}