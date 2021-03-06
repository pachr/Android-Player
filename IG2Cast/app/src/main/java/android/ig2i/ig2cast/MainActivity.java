package android.ig2i.ig2cast;


import android.content.ClipData;
import android.content.Intent;
import android.ig2i.ig2cast.activity.FragmentDrawer;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.ig2i.ig2cast.api.musiXmatchSDKActivity;
import android.ig2i.ig2cast.api.musiXmatchSDKActivitySearch;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    // Declaring Your View and Variables

    private FragmentDrawer drawerFragment;

    Toolbar mToolbar;

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;


    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    MenuItem search;
    public static MediaPlayer MP = new MediaPlayer();

    CharSequence Titles[]={"Morceaux","Artistes","Albums","Playlists","Genres"};
    int Numboftabs =5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*********************************************/
        /*          MAIN TOOLBAR                     */
        /*********************************************/

        //getSupportActionBar().hide();//Ocultar ActivityBar anterior

        // Creating The Toolbar and setting it as the Toolbar for the activity
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        //setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        /*********************************************/
        /*          MENU SLIDER RIGHT                */
        /*********************************************/

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);




        /*********************************************/
        /*          TOOLBAR WITH SLIDER              */
        /*********************************************/

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        //setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorPrimaryDark);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        //setController();

        //permissions

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (item.getTitle().equals("Search")){


            //Toast.makeText(getBaseContext(),"Coucou",Toast.LENGTH_LONG).show();
            Intent search_activity = new Intent(getBaseContext(), android.ig2i.ig2cast.SearchActivity.class);

            search_activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            getBaseContext().startActivity(search_activity);

        }



        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(MainActivity.this, "Permission allow to read your External storage", Toast.LENGTH_SHORT).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission deny to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
