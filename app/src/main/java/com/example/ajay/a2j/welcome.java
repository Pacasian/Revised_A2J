package com.example.ajay.a2j;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class welcome extends Activity
{


 @Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     requestWindowFeature(Window.FEATURE_NO_TITLE);
     setContentView(R.layout.welcome);
 }
    public void tologin(View v) {
        Intent intent = new Intent(this, com.example.ajay.a2j.MainActivity.class);
        startActivity(intent);
    }
}
