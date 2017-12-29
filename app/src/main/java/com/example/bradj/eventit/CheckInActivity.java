package com.example.bradj.eventit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CheckInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
    }

    public void onClickCancel(View view) {
        finish();
    }
}
