package com.vitaliylenchuk.films;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Film_information extends AppCompatActivity {

    private static final String FILM_NUMBER= "com.vitaliylenchuk.film_number";
    private int mCurrentNumber;
    private ImageView mImageView;
    private EditText mEditText;
    private Film[] mFilmBank = new Film[]{
            new Film(R.string.film1_description, R.drawable.t1),
            new Film(R.string.film2_description, R.drawable.t2),
    };

    public static Intent newIntent (Context packageContext, int mCurrentNumber) {
        Intent intent = new Intent (packageContext, Film_information.class);
        intent.putExtra(FILM_NUMBER, mCurrentNumber);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_information);

        mCurrentNumber = getIntent().getIntExtra(FILM_NUMBER, 0);
        mImageView = (ImageView) findViewById(R.id.film_poster);
        mImageView.setImageResource(mFilmBank[mCurrentNumber].getPoster());
        mEditText = (EditText) findViewById(R.id.film_description);
        mEditText.setText(mFilmBank[mCurrentNumber].getFilm_description());

    }
}