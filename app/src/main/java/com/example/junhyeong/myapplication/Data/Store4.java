package com.example.junhyeong.myapplication.Data;

import org.json.JSONObject;

/**
 * Created by wnsgu on 2017-10-25.
 */

public class Store4 {
    JSONObject ArrData;
    String Title, Text, Image, Created, StoreName;

    public void setData(JSONObject Data, String Title, String Text, String Image, String Created, String StoreName) {
        setArrData(Data);
        setTitle(Title);
        setText(Text);
        setImage(Image);
        setCreated(Created);
        setStoreName(StoreName);
    }

    public void setArrData(JSONObject Data) {
        ArrData = Data;
    }
    public void setTitle(String title) { Title = title;}
    public void setText(String text) {Text = text;}
    public void setImage(String image) {Image = image;}
    public void setCreated(String created) {Created = created;}
    public void setStoreName(String storename) {StoreName = storename;}

    public Store4 getData4() {
        return this;
    }
    public JSONObject getArrData4() {
        return ArrData;
    }
    public String getTitle() { return Title; }
    public String getText() { return Text;}
    public String getImage() { return Image;}
    public String getCreated() { return Created;}
    public String getStoreName() { return StoreName;}

    public int compareTo(Store4 store)
    {
        // 가게이름으로 정렬
        return this.Created.compareTo(store.Created);
        /*
        if(this.CTF_CODE < store.CTF_CODE)
        {
            return 1;
        }
        else if(this.CTF_CODE == store.CTF_CODE)
        {
            return 0;
        }
        else
        {
            return -1;
        }
        */
    }
}
