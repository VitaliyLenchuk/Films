package com.vitaliylenchuk.films;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.vitaliylenchuk.films.R.color.grey;
import static com.vitaliylenchuk.films.R.color.material_blue_grey_800;
import static com.vitaliylenchuk.films.R.color.red;

public class MainActivity extends AppCompatActivity {

    private Button mDetail1Button;
    private Button mDetail2Button;
    private TextView mFilm1;
    private TextView mFilm2;
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

        mDetail1Button = (Button) findViewById(R.id.details1);
        mDetail2Button = (Button) findViewById(R.id.details2);
        mFilm1 = (TextView) findViewById(R.id.film1);
        mFilm2 = (TextView) findViewById(R.id.film2);
        switch (mCurrentFilm){
            case 0: mFilm1.setTextColor(getResources().getColor(red));
            break;
            case 1: mFilm2.setTextColor(getResources().getColor(red));
                break;
        }
        mDetail1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mFilm1.setTextColor(getResources().getColor(red));
               mFilm2.setTextColor(getResources().getColor(grey));
               mCurrentFilm = 0;
               Intent intent = Film_information.newIntent(MainActivity.this, mCurrentFilm);
               startActivity(intent);
            }
        });
        mDetail2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilm2.setTextColor(getResources().getColor(red));
                mFilm1.setTextColor(getResources().getColor(grey));
                mCurrentFilm = 1;
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

}