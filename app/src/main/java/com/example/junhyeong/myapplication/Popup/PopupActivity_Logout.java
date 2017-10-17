package com.example.junhyeong.myapplication.Popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.junhyeong.myapplication.Login.LoginActivity;
import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-10-06.
 */

public class PopupActivity_Logout extends Activity{
    private Button Yes,No;
    private Intent logout;
    private Intent clear;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_logout);
        logout = new Intent(this, LoginActivity.class);

        Yes = (Button)findViewById(R.id.pop_logout);
        No = (Button)findViewById(R.id.pop_logout_cancel);

        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //새로 생성하려는 액티비티와 동일한 액티비티가 스택에 있을경우
                //동일한 액티비티 위의 모든 액티비티를 종료시키고 기존 액티비티를 새로 생성된 액티비티로 교체한다는 플래그
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                finish();
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
