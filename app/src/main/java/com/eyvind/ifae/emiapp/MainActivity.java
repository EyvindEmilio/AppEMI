package com.eyvind.ifae.emiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.eyvind.ifae.emiapp.REST.API;
import com.eyvind.ifae.emiapp.REST.MODELS.LOGOUT;
import com.eyvind.ifae.emiapp.REST.REST;
import com.github.pwittchen.prefser.library.Prefser;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    API service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new REST(MainActivity.this).API().create(API.class);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = Fragment_INICIO.newInstance(1);
                break;
            case 1:
                fragment = Fragment_GRADO.newInstance(position+1);
                break;
            case 2:
                fragment = Fragment_POST_GRADO.newInstance(position+1);
                break;
            case 3:
                fragment = Fragment_LOGIN.newInstance(position+1);
                break;
            case 4:
                fragment = Fragment_PARTES.newInstance(position+1);
                break;
            case 5:
                fragment = Fragment_ABOUT.newInstance(position+1);
                break;
            case 6:
                fragment = Fragment_ABOUT.newInstance(position+1);
                break;
            case 7:
                Logout();
                break;
        }
        if(position != 7){
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section_home);
                break;
            case 2:
                mTitle = getString(R.string.title_section_parts_classes);
                break;
            case 3:
                mTitle = getString(R.string.title_section_parts_sports);
                break;
            case 4:
                mTitle = getString(R.string.title_section_list_students);
                break;
            case 5:
                mTitle = getString(R.string.title_section_settings);
                break;
            case 6:
                mTitle = getString(R.string.title_section_about_emi);
                break;
            case 7:
                mTitle = getString(R.string.title_section_about);
                break;
            case 8:
                mTitle = getString(R.string.title_section_logout);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void removeTokenRequest(){
        Prefser pref = new Prefser(MainActivity.this);
        pref.remove("TOKEN");
        Log.i("TOKEN", "TOKEN DELETED");
    }
    public void startLoginActivity(){
        Intent loginActivity = new Intent(MainActivity.this,ActivityEMIapp.class);
        startActivity(loginActivity);
        finish();
    }

    public void Logout(){
        service.logout(new Callback<LOGOUT>() {
            @Override
            public void success(LOGOUT logout, Response response) {
                if(logout.isSuccess()){
                    Toast.makeText(MainActivity.this, logout.getMessage(), Toast.LENGTH_LONG).show();
                    removeTokenRequest();
                    startLoginActivity();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, getString(R.string.error_internet_connection), Toast.LENGTH_LONG).show();
            }
        });
    }

/*    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }*/
}
