package com.example.junhyeong.myapplication.Explain_Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-10-19.
 */

public class FirstFragment extends Fragment
{
    public FirstFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.first_frgment, container, false);
        return layout;
    }
}
