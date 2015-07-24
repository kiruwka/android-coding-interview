package android.coding.interview.makeitawesome;

import android.coding.interview.makeitawesome.fragment.PicturesFragment;
import android.coding.interview.makeitawesome.fragment.WelcomeScreenFragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup action bar
        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        
        // setting up action bar icon and home navigation
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        
        
        // drawer layout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
           navigationView.setNavigationItemSelectedListener(this); 
        }

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.content_frame) == null) { // no fragment visible yet -> create default one
           fm.beginTransaction().add(R.id.content_frame, WelcomeScreenFragment.newInstance()).commit();
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
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // handling items selection in Menu Drawer
        //menuItem.setChecked(true);
        switch (menuItem.getItemId()) {
            case R.id.navigation_photos:
                FragmentManager fm = getSupportFragmentManager();
                if (fm.findFragmentByTag("PICTURES_FRAGMENT") == null) { // no pictures shown yet, show it
                    fm.beginTransaction().replace(R.id.content_frame, PicturesFragment.newInstance(), "PICTURES_FRAGMENT").addToBackStack(null).commit();
                }
        }
        
        mDrawerLayout.closeDrawers();
        return true;
    }
}
