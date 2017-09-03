package com.example.junhyeong.myapplication.Data;

import org.json.JSONObject;

/**
 * Created by Junhyeong on 2017-09-03.
 */

public class Store {
    JSONObject ArrData;
    int CTF_CODE, CTF_TYPE;
    String CTF_TYPE_NAME, CTF_NAME, CTF_ADDR, CTF_TEL;
    double CTF_X, CTF_Y;

    public void setData(JSONObject DATA, int CODE, int TYPE, String TYPE_NAME, String NAME, double X, double Y, String ADDR, String TEL) {
        setArrData(DATA);
        setCTF_CODE(CODE);
        setCTF_TYPE(TYPE);
        setCTF_TYPE_NAME(TYPE_NAME);
        setCTF_NAME(NAME);
        setCTF_X(X);
        setCTF_Y(Y);
        setCTF_ADDR(ADDR);
        setCTF_TEL(TEL);
    }

    public void setArrData(JSONObject Data) {
        ArrData = Data;
    }

    public void setCTF_CODE(int CODE) {
        CTF_CODE = CODE;
    }

    public void setCTF_TYPE(int TYPE) {
        CTF_TYPE = TYPE;
    }

    public void setCTF_TYPE_NAME(String TYPE_NAME) {
        CTF_TYPE_NAME = TYPE_NAME;
    }

    public void setCTF_NAME(String NAME) {
        CTF_NAME = NAME;
    }

    public void setCTF_X(double X) {
        CTF_X = X;
    }

    public void setCTF_Y(double Y) {
        CTF_Y = Y;
    }

    public void setCTF_ADDR(String ADDR) {
        CTF_ADDR = ADDR;
    }

    public void setCTF_TEL(String TEL) {
        CTF_TEL = TEL;
    }

    public Store getData1() {
        return this;
    }

    public JSONObject getArrData() {
        return ArrData;
    }

    public int getCTF_CODE() {
        return CTF_CODE;
    }

    public int getCTF_TYPE() {
        return CTF_TYPE;
    }

    public String getCTF_TYPE_NAME() {
        return CTF_TYPE_NAME;
    }

    public String getCTF_NAME()
    {
        return CTF_NAME;
    }
    public double getCTF_X()
    {
        return CTF_X;
    }
    public double getCTF_Y()
    {
        return CTF_Y;
    }
    public String getCTF_ADDR()
    {
        return CTF_ADDR;
    }
    public String getCTF_TEL()
    {
        return CTF_TEL;
    }
}
