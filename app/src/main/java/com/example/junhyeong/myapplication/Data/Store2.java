package com.example.junhyeong.myapplication.Data;

import org.json.JSONObject;

/**
 * Created by yeonjin on 2017-09-22.
 */

public class Store2 implements Comparable<Store2> {
    JSONObject ArrData2;
    int CRTFC_UPSO_MGT_SNO;
    String UPSO_NM, CGG_CODE_NM, COB_CODE_NM, BIZCND_CODE_NM,OWNER_NM,
            CRTFC_GBN_NM,CRTFC_CHR_NM,CRTFC_YMD,USE_YN,TEL_NO,RDN_DETAIL_ADDR,RDN_CODE_NM,CRT_USR,FOOD_MENU;
    double Y_DNTS, X_CNTS;

    public void setData(JSONObject DATA, int CRTFC_UPSO_MGT_SNO, String UPSO_NM, String CGG_CODE_NM, String COB_CODE_NM, String BIZCND_CODE_NM, String OWNER_NM, String CRTFC_GBN_NM, String CRTFC_CHR_NM, String CRTFC_YMD, String USE_YN, int Y_DNTS, int X_CNTS, String TEL_NO,String RDN_DETAIL_ADDR, String RDN_CODE_NM,String CRT_USR,String FOOD_MENU ) {
        setArrData2(DATA);
        setCRTFC_UPSO_MGT_SNO(CRTFC_UPSO_MGT_SNO);
        setUPSO_NM(UPSO_NM);
        setCGG_CODE_NM(CGG_CODE_NM);
        setCOB_CODE_NM(COB_CODE_NM);
        setBIZCND_CODE_NM(BIZCND_CODE_NM);
        setOWNER_NM(OWNER_NM);
        setCRTFC_GBN_NM(CRTFC_GBN_NM);
        setCRTFC_CHR_NM(CRTFC_CHR_NM);
        setCRTFC_YMD(CRTFC_YMD);
        setUSE_YN(USE_YN);
        setY_DNTS(Y_DNTS);
        setX_CNTS(X_CNTS);
        setTEL_NO(TEL_NO);
        setRDN_DETAIL_ADDR(RDN_DETAIL_ADDR);
        setRDN_CODE_NM(RDN_CODE_NM);
        setCRT_USR(CRT_USR);
        setFOOD_MENU(FOOD_MENU);
    }

    public void setArrData2(JSONObject Data) {
        ArrData2 = Data;
    }
    public void setCRTFC_UPSO_MGT_SNO(int CODE) {
        CRTFC_UPSO_MGT_SNO = CODE;
    }
    public void setUPSO_NM(String UPSO) {
        UPSO_NM = UPSO;
    }
    public void setCGG_CODE_NM(String CGG) {
        CGG_CODE_NM = CGG;
    }
    public void setCOB_CODE_NM(String COB) {
        COB_CODE_NM = COB;
    }
    public void setBIZCND_CODE_NM(String BIZCND) {
        BIZCND_CODE_NM = BIZCND;
    }
    public void setOWNER_NM(String OWNER) {
        OWNER_NM = OWNER;
    }
    public void setCRTFC_GBN_NM(String CRTFC_GBN) {
        CRTFC_GBN_NM = CRTFC_GBN;
    }
    public void setCRTFC_CHR_NM(String CRTFC_CHR) {
        CRTFC_CHR_NM = CRTFC_CHR;
    }
    public void setCRTFC_YMD(String YMD) {
        CRTFC_YMD = YMD;
    }
    public void setUSE_YN(String YN) {
        USE_YN = YN;
    }
    public void setY_DNTS(double X) {
        Y_DNTS = X;
    }
    public void setX_CNTS(double Y) {
        X_CNTS = Y;
    }
    public void setTEL_NO(String TEL) {
        TEL_NO = TEL;
    }
    public void setRDN_DETAIL_ADDR(String ADDR) {
        RDN_DETAIL_ADDR = ADDR;
    }
    public void setRDN_CODE_NM(String RDN) {
        RDN_CODE_NM = RDN;
    }
    public void setCRT_USR(String CRT) {
        CRT_USR = CRT;
    }
    public void setFOOD_MENU(String FOOD) {
        FOOD_MENU = FOOD;
    }

    public Store2 getData1() {
        return this;
    }
    public JSONObject getArrData2() {
        return ArrData2;
    }
    public int getCRTFC_UPSO_MGT_SNO() {
        return CRTFC_UPSO_MGT_SNO;
    }
    public String getUPSO_NM() {
        return UPSO_NM;
    }
    public String getCGG_CODE_NM()
    {
        return CGG_CODE_NM;
    }
    public String getCOB_CODE_NM()
    {
        return COB_CODE_NM;
    }
    public String getBIZCND_CODE_NM()
    {
        return BIZCND_CODE_NM;
    }
    public String getOWNER_NM()
    {
        return OWNER_NM;
    }
    public String getCRTFC_GBN_NM()
    {
        return CRTFC_GBN_NM;
    }
    public String getCRTFC_CHR_NM()
    {
        return CRTFC_CHR_NM;
    }
    public String getCRTFC_YMD()
    {
        return CRTFC_YMD;
    }
    public String getUSE_YN()
    {
        return USE_YN;
    }
    public double getY_DNTS()
    {
        return Y_DNTS;
    }
    public double getX_CNTS()
    {
        return X_CNTS;
    }
    public String getTEL_NO()
    {
        return TEL_NO;
    }
    public String getRDN_DETAIL_ADDR()
    {
        return RDN_DETAIL_ADDR;
    }
    public String getRDN_CODE_NM()
    {
        return RDN_CODE_NM;
    }
    public String getCRT_USR()
    {
        return CRT_USR;
    }
    public String getFOOD_MENU()
    {
        return FOOD_MENU;
    }

    public int compareTo(Store2 store)
    {
        // 가게이름으로 정렬
        return this.UPSO_NM.compareTo(store.UPSO_NM);
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

