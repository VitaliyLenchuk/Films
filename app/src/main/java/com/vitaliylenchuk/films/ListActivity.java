package com.vitaliylenchuk.films;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ListActivity extends SingleFragmentActivity {


    protected Fragment createFragment() {
        return new FilmListFragment();
    }

   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                Film film = new Film();
                FilmLab.get(getApplicationContext()).addFilm(film);
                Intent intent = FilmActivity
                        .newIntent(getApplicationContext(), film.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}