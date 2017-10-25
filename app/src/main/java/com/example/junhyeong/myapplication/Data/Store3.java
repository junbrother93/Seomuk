package com.example.junhyeong.myapplication.Data;

import org.json.JSONObject;

/**
 * Created by wnsgu on 2017-10-25.
 */

public class Store3 {
    JSONObject ArrData;
    String Classify, Name, Image;
    int StoreId;

    public void setData(JSONObject Data, String classify, String name, String image, int StoreId) {
        setArrData(Data);
        setClassify(classify);
        setImage(image);
        setName(name);
        setStoreId(StoreId);
    }

    public void setArrData(JSONObject Data) {
        ArrData = Data;
    }
    public void setClassify(String classify) { Classify = classify;}
    public void setName(String name) {Name = name;}
    public void setImage(String image) {Image = image;}
    public void setStoreId(int storeid) {StoreId = storeid;}

    public Store3 getData3() {
        return this;
    }
    public JSONObject getArrData3() {
        return ArrData;
    }
    public String getClassify() {
        return Classify;
    }
    public String getName() {
        return Name;
    }
    public String getImage() {
        return Image;
    }
    public int getStoreId() {
        return StoreId;
    }

    public int compareTo(Store3 store)
    {
        // 가게이름으로 정렬
        return this.Classify.compareTo(store.Classify);
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
