package com.example.junhyeong.myapplication.Popup;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.junhyeong.myapplication.R;

public class PopupActivity_Explain extends Activity{
    private ViewPager mPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
    }


    private void setCurrentInflateItem(int type){
        if(type==0){
            mPager.setCurrentItem(0);
        }else if(type==1){
            mPager.setCurrentItem(1);
        }else if(type==2){
            mPager.setCurrentItem(2);
        }
        else if(type==3){
            mPager.setCurrentItem(3);
        }
        else if(type==4){
            mPager.setCurrentItem(4);
        }
        else if(type==5){
            mPager.setCurrentItem(5);
        }
        else if(type==6){
            mPager.setCurrentItem(6);
        }
        else
            mPager.setCurrentItem(7);
    }

    /**
     * PagerAdapter
     */
    public class PagerAdapterClass extends PagerAdapter{

        private LayoutInflater mInflater;

        public PagerAdapterClass(Context c){
            super();
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object instantiateItem(View pager, int position) {
            View v = null;
            if(position==0){
                v = mInflater.inflate(R.layout.inflate_one, null);
                v.findViewById(R.id.iv_one);

            }
            else if(position==1){
                v = mInflater.inflate(R.layout.inflate_two, null);
                v.findViewById(R.id.iv_two);

            }else if(position==2){
                v = mInflater.inflate(R.layout.inflate_three, null);
                v.findViewById(R.id.iv_three);
            }else if(position==3){
                v = mInflater.inflate(R.layout.inflate_four, null);
                v.findViewById(R.id.iv_four);
            }else if(position==4){
                v = mInflater.inflate(R.layout.inflate_five, null);
                v.findViewById(R.id.iv_five);
            }else if(position==5){
                v = mInflater.inflate(R.layout.inflate_six, null);
                v.findViewById(R.id.iv_six);
            }else if(position==6){
                v = mInflater.inflate(R.layout.inflate_seven, null);
                v.findViewById(R.id.iv_seven);
            }else{
                v = mInflater.inflate(R.layout.inflate_eight, null);
                v.findViewById(R.id.iv_eight);
            }

            ((ViewPager)pager).addView(v, 0);

            return v;
        }

        @Override
        public void destroyItem(View pager, int position, Object view) {
            ((ViewPager)pager).removeView((View)view);
        }

        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj;
        }

        @Override public void restoreState(Parcelable arg0, ClassLoader arg1) {}
        @Override public Parcelable saveState() { return null; }
        @Override public void startUpdate(View arg0) {}
        @Override public void finishUpdate(View arg0) {}
    }

}














