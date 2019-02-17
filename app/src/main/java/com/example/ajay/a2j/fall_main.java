package com.example.ajay.a2j;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class fall_main extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fall_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_news()).commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                fall_main.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.b_navigationView);
                findViewById(R.id.b_navigationView);
                //for bottom nav items to change
        Menu b_menu=bottomNavigationView.getMenu();
        MenuItem menuItem=b_menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment=null;
                switch (menuItem.getItemId()) {
                    case R.id.navigation_news:
                        Toast.makeText(getApplication(), "item 1 was clicked", Toast.LENGTH_SHORT).show();
                        selectedFragment= new fragment_news();
                        break;

                    case R.id.navigation_lawyer:
                        Toast.makeText(getApplication(), "item 2 was clicked", Toast.LENGTH_SHORT).show();
                        selectedFragment= new fragment_lawyer();
                        break;

                    case R.id.navigation_document:
                        Toast.makeText(getApplication(), "item 3 was clicked", Toast.LENGTH_SHORT).show();
                        selectedFragment= new fragment_document();
                        break;

                    case R.id.navigation_chat:
                        Toast.makeText(getApplication(), "item 4 was clicked", Toast.LENGTH_SHORT).show();
                        selectedFragment= new fragment_discuss();
                        break;

                    case R.id.navigation_quora:
                        Toast.makeText(getApplication(), "item 5 was clicked", Toast.LENGTH_SHORT).show();
                        selectedFragment= new fragment_qa();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }

        });
        //bottom navigation ends


        }
    public void to_profile(View v) {
        Intent intent = new Intent(this, com.example.ajay.a2j.user_profile.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // set item as selected to persist highlight
        menuItem.setChecked(true);
        // Handle navigation view item clicks here.

        switch (menuItem.getItemId()) {

            case R.id.side_study:
                Toast.makeText(getApplication(), "item 1 was clicked", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(fall_main.this,study_material.class);
                startActivity(intent1);
                break;

            case R.id.side_law:
                Toast.makeText(getApplication(), "item 2 was clicked", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(fall_main.this,side_law.class);
                startActivity(intent2);
                break;
            case R.id.side_legal_info:
                Toast.makeText(getApplication(), "item 3 was clicked", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(fall_main.this,side_legal_info.class);
                startActivity(intent3);
                break;
            case R.id.nav_share:
                Toast.makeText(getApplication(), "item 4 was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.side_help:
                Toast.makeText(getApplication(), "item 5 was clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
