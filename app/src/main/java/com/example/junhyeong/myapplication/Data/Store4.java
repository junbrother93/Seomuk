package com.example.junhyeong.myapplication.Data;

import org.json.JSONObject;

/**
 * Created by wnsgu on 2017-10-25.
 */

public class Store4 {
    JSONObject ArrData;
<<<<<<< HEAD
    String Title, Text, Image, Created, StoreName;

    public void setData(JSONObject Data, String Title, String Text, String Image, String Created, String StoreName) {
=======
    String Title, Text, Image, Created;
    int Score;

    public void setData(JSONObject Data, String Title, String Text, String Image, String Created, int Score) {
>>>>>>> origin/수정중_-_준형
        setArrData(Data);
        setTitle(Title);
        setText(Text);
        setImage(Image);
        setCreated(Created);
<<<<<<< HEAD
        setStoreName(StoreName);
=======
        setScore(Score);
>>>>>>> origin/수정중_-_준형
    }

    public void setArrData(JSONObject Data) {
        ArrData = Data;
    }
    public void setTitle(String title) { Title = title;}
    public void setText(String text) {Text = text;}
    public void setImage(String image) {Image = image;}
    public void setCreated(String created) {Created = created;}
<<<<<<< HEAD
    public void setStoreName(String storename) {StoreName = storename;}
=======
    public void setScore(int score) {Score = score;}
>>>>>>> origin/수정중_-_준형

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
<<<<<<< HEAD
    public String getStoreName() { return StoreName;}
=======
    public int getScore() { return Score;}
>>>>>>> origin/수정중_-_준형

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
<<<<<<< HEAD
=======

>>>>>>> origin/수정중_-_준형
