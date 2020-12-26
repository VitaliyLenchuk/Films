package com.vitaliylenchuk.films;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class FavListActivity extends SingleFragmentActivity {
    private static final String DIALOG_INFO = "DialogInfo";

    protected Fragment createFragment() {
        return new FavFilmFragment();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        ((MenuInflater) inflater).inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, "Пользуйся киноприложением!");
                i=Intent.createChooser(i, getString(R.string.share));
                startActivity(i);
                return true;
            case R.id.new_film:
                RecyclerView view=findViewById(R.id.film_recycler_view);
                final Snackbar snackbar = Snackbar.
                        make(view, "Вы действительно хотите добавить фильм?", Snackbar.LENGTH_SHORT);

                // Устанавливаем цвет текста кнопки действий
                snackbar.setActionTextColor(getResources().getColor(R.color.indigo));
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Film film = new Film();
                        FilmLab.get(getApplicationContext()).addFilm(film);
                        Intent intent = FilmActivity
                                .newIntent(getApplicationContext(), film.getId());
                        startActivity(intent);
                    }
                });

                // Получение snackbar view
                View snackbarView = snackbar.getView();

                // Изменение цвета текста
                TextView textView;
                textView = (TextView) snackbar.getView()
                        .findViewById(com.google.android.material.R.id.snackbar_text);
                textView.setTextColor(getResources().getColor(android.R.color.white));

                // Изменение цвета фона
                snackbarView.setBackgroundColor(Color.GRAY);


                //Создание и добавление Snackbar.Callback
                Snackbar.Callback snCallback = new Snackbar.Callback() {
                    public void onShown(Snackbar sb) {
                        //TODO
                    }

                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        //TODO
                    }
                };
                snackbar.addCallback(snCallback);

                snackbar.show();

                //Убрать Snackbar.Callback
                snackbar.removeCallback(snCallback);

                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //snackbar.dismiss();
                    }
                }, 5000);
                return true;

            case R.id.nav_home:
                Intent intent = new Intent (this, ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_info:
                FragmentManager manager = getSupportFragmentManager();
                App_info dialog = new App_info();
                dialog.show(manager, DIALOG_INFO);
                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_fav:
                return true;

            case R.id.nav_exit:
                finish(); System.exit(0);
                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;








            default:
                return super.onOptionsItemSelected(item);
        }
    }
}