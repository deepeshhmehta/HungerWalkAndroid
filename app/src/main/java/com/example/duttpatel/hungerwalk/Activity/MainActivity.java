package com.example.duttpatel.hungerwalk.Activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.duttpatel.hungerwalk.Fragment.FavouriteFragment;
import com.example.duttpatel.hungerwalk.Fragment.HistoryFragment;
import com.example.duttpatel.hungerwalk.Fragment.ProfileFragment;
import com.example.duttpatel.hungerwalk.Fragment.ReportIssueFragment;
import com.example.duttpatel.hungerwalk.Fragment.ResaturantFragment;
import com.example.duttpatel.hungerwalk.R;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private View navHeader;
    private TextView nav_header_username_textview;
    private Toolbar app_toolbar;


    public static int navItemIndex = 0;


    private String titlename="";
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    //navigation view tag
    private static final String TAG_HOME = "home";
    private static final String TAG_RESTAURANT = "restaurant";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_HISTORY = "history";
    private static final String TAG_FAVOURITE = "favourite";
    private static  final  String TAG_ISSUE="issue";
    private static final String TAG_LOGOUT = "logout";
    public static String CURRENT_TAG = TAG_RESTAURANT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        app_toolbar=(Toolbar)findViewById(R.id.app_toolbar);
        setSupportActionBar(app_toolbar);



        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        //nnavigation view setup
        navigationView=(NavigationView)findViewById(R.id.nav_view);


        //navigation header and its control
        navHeader=navigationView.getHeaderView(0);
        nav_header_username_textview=(TextView)navHeader.findViewById(R.id.nav_header_username_textview);


        //setting user name in navigation header
        nav_header_username_textview.setText("Dutt Patel");


        // initializing navigation menu
        setUpNavigationView();


        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_RESTAURANT;
            loadHomeFragment();
        }

    }



    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_restaurant:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_RESTAURANT;
                        break;
                    case R.id.nav_favourite:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_FAVOURITE;
                        break;
                    case R.id.nav_profile:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_PROFILE;
                        break;
                    case R.id.nav_history:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_HISTORY;
                        break;
                    case R.id.nav_logout:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_LOGOUT;
                        break;
                    case R.id.nav_issue:
                        navItemIndex = 4;
                        CURRENT_TAG=TAG_ISSUE;
                        break;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }


                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, app_toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void loadHomeFragment() {
        selectNavMenu();

        setToolBarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawerLayout.closeDrawers();
            return;
        }

        Fragment fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.app_frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();

        /*Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments

            }
        };

        // If mPendingRunnable is not null, then add to the message queue

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }*/


        drawerLayout.closeDrawers();

        invalidateOptionsMenu();

    }

    private Fragment getHomeFragment() {
        switch(navItemIndex) {
            case 0:
                ResaturantFragment restaturantfragment = new ResaturantFragment();
                return restaturantfragment;
            case 1:
                HistoryFragment historyfragment = new HistoryFragment();
                return historyfragment;
            case 2:
                ProfileFragment profilefragment = new ProfileFragment();
                return profilefragment;
            case 3:
                FavouriteFragment favouritefragment = new FavouriteFragment();
                return favouritefragment;
            case 4:
                ReportIssueFragment reportIssueFragment=new ReportIssueFragment();
                return reportIssueFragment;
                default:
                    return new ResaturantFragment();

        }
    }

    private void setToolBarTitle() {

        switch(navItemIndex){
            case 0:
                getSupportActionBar().setTitle("Resaturant");
                break;
            case 1:
                getSupportActionBar().setTitle("History");
                break;
            case 2:
                getSupportActionBar().setTitle("Profile");
                break;
            case 3:
                getSupportActionBar().setTitle("Favourite");
                break;
            case 4:
                getSupportActionBar().setTitle("Report issue");
                break;

        }
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }



}