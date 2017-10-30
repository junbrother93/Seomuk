package com.example.junhyeong.myapplication.Main;

import android.graphics.drawable.Drawable;

/**
 * Created by jin on 2017-08-15.
 */

public class ListViewItem_review_mypage {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String Date;
    private String Store_Name;


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setStore_Name(String store) {
        Store_Name = store ;
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
    public String getStore_Name() {return this.Store_Name ;   }
    public String getTitle() {return this.titleStr ;   }
    public String getDate() {return this.Date ;   }


}
