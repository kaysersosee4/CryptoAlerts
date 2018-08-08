package com.example.konrad.cryptoalerts.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.activities.Home.HomeFragment;
import com.example.konrad.cryptoalerts.activities.Wallet.WalletFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;


    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);



    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        Bundle args = new Bundle();
        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentClass = HomeFragment.class;
                args.putBoolean("FAVOURITE", false);
                break;
            case R.id.nav_favourite:
                fragmentClass = HomeFragment.class;
                args.putBoolean("FAVOURITE", true);
                break;
            case R.id.nav_wallet:
                fragmentClass = WalletFragment.class;
                break;
            /*case R.id.nav_alerts:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = ThirdFragment.class;
                break;*/
            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(args);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

}