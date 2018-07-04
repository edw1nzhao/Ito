package com.tomoed.ito.controller;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tomoed.ito.R;
import com.tomoed.ito.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton navigationCloseButton;
    private TextView titleNav;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private View navigationHeaderView;

    private Fragment mainFrag = null;
    private Fragment currFrag = null;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private User user;
    private boolean main = true;

    private static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (User) getIntent().getSerializableExtra("USER");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nvView);

        toolbarSetup();
        drawLayoutSetup();
        navigationViewSetup();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.nav_button_close) {
            drawerLayout.closeDrawers();
        }
    }

    //Method related to Toolbar.
    public void toolbarSetup() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Method related to the DrawerLayout.
    private void drawLayoutSetup() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open,  R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    selectDrawerItem(menuItem);
                    return true;
                }
            });
    }

    //Method related to the NavigationView.
    public void navigationViewSetup() {
        try {
            Class fragClass = MainFragment.class;
            mainFrag = (Fragment) fragClass.newInstance();
        } catch (Exception error) {
            Log.d(TAG, error.getMessage());
        }
        fragmentManager.beginTransaction().replace(R.id.flContent, mainFrag).commit();

        //Setup NavigationView close button.e
        navigationHeaderView = navigationView.getHeaderView(0);
        navigationHeaderView.findViewById(R.id.nav_button_close).setOnClickListener(this);

        //Setup NavigationView username.
        titleNav = navigationHeaderView.findViewById(R.id.nav_title);
        titleNav.setText(user.getFirstName() + " " + user.getLastName());
    }

    //Method related to ActionBarDrawerToggle.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Method related to ActionBarDrawerToggle.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    //Method related to ActionBarDrawerToggle.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //Method related to the NavigationView.
    public void selectDrawerItem(MenuItem menuItem) {
        Class fragmentClass;
        Fragment tempFrag = null;

        switch(menuItem.getItemId()) {
            case R.id.nav_map:
                fragmentClass = MainFragment.class;
                break;
            case R.id.nav_list:
                fragmentClass = ListFragment.class;
                break;
            case R.id.nav_account:
                fragmentClass = AccFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = SettingFragment.class;
                break;
            default:
                fragmentClass = MainFragment.class;
        }

        try {
            tempFrag = (Fragment) fragmentClass.newInstance();
        } catch (Exception error) {
            Log.d(TAG, error.getMessage());
        }

        // Insert the fragment by replacing any existing fragment.
        FragmentManager fm = getSupportFragmentManager();

        if (main) {
            if (!fragmentClass.equals(MainFragment.class)) {
                fm.beginTransaction().hide(mainFrag).commit();
                fm.beginTransaction().add(R.id.flContent, tempFrag).commit();
                main = false;
                currFrag = tempFrag;
            }
        } else {
            if (fragmentClass.equals(MainFragment.class)) {
                fm.beginTransaction().remove(currFrag).commit();
                fm.beginTransaction().show(mainFrag).commit();
                main = true;
                currFrag = null;
            } else {
                fm.beginTransaction().remove(currFrag).commit();
                fm.beginTransaction().replace(R.id.flContent, tempFrag).commit();
                currFrag = tempFrag;
            }
        }
        // Highlight the selected item has been done by NavigationView.
        menuItem.setChecked(true);
        // Set ActionBar title.
        setTitle(menuItem.getTitle());
        // Close the NavigationView.
        drawerLayout.closeDrawers();
    }
}
