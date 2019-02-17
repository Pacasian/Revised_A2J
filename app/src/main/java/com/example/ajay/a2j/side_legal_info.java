package com.example.ajay.a2j;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class side_legal_info extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.side_legal_info);
    }
    public void to_fall_main(View v) {
        Intent intent = new Intent(this, com.example.ajay.a2j.fall_main.class);
        finish();
        startActivity(intent);
    }

}
