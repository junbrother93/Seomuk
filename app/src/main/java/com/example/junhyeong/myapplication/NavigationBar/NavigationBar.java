package com.example.junhyeong.myapplication.NavigationBar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.junhyeong.myapplication.R;

/**
 * Created by jin on 2017-08-14.
 */

public class NavigationBar extends LinearLayout {
    Context context;

    public NavigationBar(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.navigation_bar, this, true);
    }
}
