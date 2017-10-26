package com.example.junhyeong.myapplication.Data;

import org.json.JSONObject;

/**
 * Created by wnsgu on 2017-10-01.
 */

public class Review implements Comparable<Review> {
    JSONObject ArrReviewData;
    String title, body, store_name, created, classify, image;
    int review_id, user_id, store_id;
    int score;

    public void setData(JSONObject DATA, String Title, String Body, String Storename, String Created, String Classify, String Image, int Score, int Index, int User_id, int Store_id) {
        setArrReviewData(DATA);
        setTitle(Title);
        setBody(Body);
        setStore_name(Storename);
        setCreated(Created);
        setClassify(Classify);
        setImage(Image);
        setScore(Score);
        setReview_id(Index);
        setUser_id(User_id);
        setStore_id(Store_id);
    }

    public void setArrReviewData(JSONObject Data) {
        ArrReviewData = Data;
    }
    public void setTitle(String Title) {
        title = Title;
    }
    public void setBody(String Body){
        body = Body;
    }
    public void setStore_name(String Storename) {store_name = Storename;}
    public void setCreated(String Created) {created = Created;}
    public void setClassify(String Classify) { classify = Classify; }
    public void setImage(String Image) { image = Image; }
    public void setScore(int Score){
        score = Score;
    }
    public void setReview_id(int Index){
        review_id = Index;
    }
    public void setUser_id(int User_id){
        user_id = User_id;
    }
    public void setStore_id(int Store_id) { store_id = Store_id; }

    public Review getData1() {
        return this;
    }
    public JSONObject getArrReviewData() {
        return ArrReviewData;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {return body;}
    public String getStore_name() {return store_name;}
    public String getCreated() {return created;}
    public String getClassify() {return classify;}
    public String getImage() {return image;}
    public int getScore() {return score;}
    public int getReview_id() {return review_id;}
    public int getUser_id() {return user_id;}
    public int getStore_id() {return store_id;}

    public int compareTo(Review review)
    {
        // 가게이름으로 정렬
        return this.title.compareTo(review.getTitle());
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
