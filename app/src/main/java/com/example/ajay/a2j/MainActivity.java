package com.example.ajay.a2j;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }
    public void to_or(View v) {
        Intent intent = new Intent(this, com.example.ajay.a2j.or.class);
        startActivity(intent);
    }
    public void tomain(View v) {
        Intent intent = new Intent(this, com.example.ajay.a2j.fall_main.class);
        finish();
        startActivity(intent);
    }


}
