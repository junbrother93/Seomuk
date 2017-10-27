package com.example.junhyeong.myapplication.Main;

import android.graphics.drawable.Drawable;

/**
 * Created by jin on 2017-08-15.
 */

public class ListViewItem_review {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String Date;


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDate(String date) {
        Date = date ;
    }


    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {return this.titleStr ;   }
    public String getDate() {return this.Date ;   }

}
