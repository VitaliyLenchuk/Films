package com.vitaliylenchuk.films;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import java.util.UUID;

public class FilmActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    private static final String TAG = "Films";
    private int mCurrentFilm = -1;
    private static final String EXTRA_FILM_ID = "film_id";
    private static final String DIALOG_INFO = "DialogInfo";

    public static Intent newIntent(Context packageContext, int filmId){
        Intent intent = new Intent(packageContext, FilmActivity.class);
        intent.putExtra(EXTRA_FILM_ID, filmId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mCurrentFilm = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            int filmId = (int) getIntent()
                    .getSerializableExtra(EXTRA_FILM_ID);
            fragment = FilmFragment.newInstance(filmId);
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);
       Log.i(TAG, "onSaveInstance");
       savedInstanceState.putInt(KEY_INDEX, mCurrentFilm);
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
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent (this, ListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_info) {
            FragmentManager manager = getSupportFragmentManager();
            App_info dialog = new App_info();
            dialog.show(manager, DIALOG_INFO);
        } else if (id == R.id.nav_fav) {
            Intent intent = new Intent (this, FavListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_exit) {
            finish();
            System.exit(0);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}