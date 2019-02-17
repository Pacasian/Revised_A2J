package com.example.ajay.a2j;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class user_profile extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.user_profile);
    }
    public void to_fall_main(View v) {
        Intent intent = new Intent(this, com.example.ajay.a2j.fall_main.class);
        finish();
        startActivity(intent);
    }
}
