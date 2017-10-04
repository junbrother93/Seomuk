package com.example.junhyeong.myapplication.Data;

import org.json.JSONObject;

/**
 * Created by wnsgu on 2017-10-01.
 */

public class Review implements Comparable<Review> {
    JSONObject ArrReviewData;
    String title, body;
    int score, index, user_id;

    public void setData(JSONObject DATA, String Title, String Body, int Score, int Index, int User_id) {
        setArrReviewData(DATA);
        setTitle(Title);
        setBody(Body);
        setScore(Score);
        setIndex(Index);
        setUser_id(User_id);
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
    public void setScore(int Score){
        score = Score;
    }
    public void setIndex(int Index){
        index = Index;
    }
    public void setUser_id(int User_id){
        user_id = User_id;
    }

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
    public int getScore() {return score;}
    public int getIndex() {return index;}
    public int getUser_id() {return user_id;}

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
