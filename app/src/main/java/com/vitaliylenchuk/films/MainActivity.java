package com.vitaliylenchuk.films;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.vitaliylenchuk.films.R.color.grey;
import static com.vitaliylenchuk.films.R.color.indigo;
import static com.vitaliylenchuk.films.R.color.material_blue_grey_800;
import static com.vitaliylenchuk.films.R.color.purple_700;
import static com.vitaliylenchuk.films.R.color.red;

public class MainActivity extends AppCompatActivity {

    private Button mDetail1Button;
    private Button mDetail2Button;
    private Button mDetail3Button;
    private Button mDetail4Button;
    private TextView mFilm1;
    private TextView mFilm2;
    private TextView mFilm3;
    private TextView mFilm4;
    private static final String KEY_INDEX = "index";
    private static final String TAG = "Films";
    private int mCurrentFilm = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mCurrentFilm = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mDetail1Button = findViewById(R.id.details1);
        mDetail2Button = (Button) findViewById(R.id.details2);
        mDetail3Button = (Button) findViewById(R.id.details3);
        mDetail4Button = (Button) findViewById(R.id.details4);
        mFilm1 = (TextView) findViewById(R.id.film1);
        mFilm2 = (TextView) findViewById(R.id.film2);
        mFilm3 = (TextView) findViewById(R.id.film3);
        mFilm4 = (TextView) findViewById(R.id.film4);
        switch (mCurrentFilm){
            case 0: mFilm1.setTextColor(getResources().getColor(purple_700));
                break;
            case 1: mFilm2.setTextColor(getResources().getColor(purple_700));
                break;
            case 2: mFilm3.setTextColor(getResources().getColor(purple_700));
                break;
            case 3: mFilm4.setTextColor(getResources().getColor(purple_700));
                break;

        }
        mDetail1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilm1.setTextColor(getResources().getColor(purple_700));
                mFilm2.setTextColor(getResources().getColor(indigo));
                mFilm3.setTextColor(getResources().getColor(indigo));
                mFilm4.setTextColor(getResources().getColor(indigo));
                mCurrentFilm = 0;
                Intent intent = Film_information.newIntent(MainActivity.this, mCurrentFilm);
                startActivity(intent);
            }
        });
        mDetail2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilm2.setTextColor(getResources().getColor(purple_700));
                mFilm1.setTextColor(getResources().getColor(indigo));
                mFilm3.setTextColor(getResources().getColor(indigo));
                mFilm4.setTextColor(getResources().getColor(indigo));
                mCurrentFilm = 1;
                Intent intent = Film_information.newIntent(MainActivity.this, mCurrentFilm);
                startActivity(intent);
            }
        });
        mDetail3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilm3.setTextColor(getResources().getColor(purple_700));
                mFilm1.setTextColor(getResources().getColor(indigo));
                mFilm2.setTextColor(getResources().getColor(indigo));
                mFilm4.setTextColor(getResources().getColor(indigo));
                mCurrentFilm = 2;
                Intent intent = Film_information.newIntent(MainActivity.this, mCurrentFilm);
                startActivity(intent);
            }
        });
        mDetail4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilm4.setTextColor(getResources().getColor(purple_700));
                mFilm1.setTextColor(getResources().getColor(indigo));
                mFilm3.setTextColor(getResources().getColor(indigo));
                mFilm2.setTextColor(getResources().getColor(indigo));
                mCurrentFilm = 3;
                Intent intent = Film_information.newIntent(MainActivity.this, mCurrentFilm);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);
       Log.i(TAG, "onSaveInstance");
       savedInstanceState.putInt(KEY_INDEX, mCurrentFilm);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}