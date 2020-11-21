package com.vitaliylenchuk.films;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class FilmFragment extends Fragment {

    private static final String ARG_FILM_ID= "com.vitaliylenchuk.film_id";
    private ImageView mPoster;
    private EditText mDescription;
    private EditText mTitle;
    private Film mFilm;

    public static FilmFragment newInstance(UUID filmId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_FILM_ID, filmId);

        FilmFragment fragment = new FilmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID filmId = (UUID) getArguments().getSerializable(ARG_FILM_ID);
        mFilm = FilmLab.get(getActivity()).getFilm(filmId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstance){
        View v = inflater.inflate(R.layout.content_main, container, false);


        mPoster = v.findViewById(R.id.film_poster);
        mTitle = v.findViewById(R.id.film_title);
        mPoster.setImageResource(mFilm.getPoster());
        mTitle.setText(mFilm.getTitle());
        mDescription = v.findViewById(R.id.film_description);
        mDescription.setText(mFilm.getFilm_description());
        if (mFilm.getFilm_description()==null) {
            mTitle.setEnabled(true);
            mDescription.setEnabled(true);
        }
        mDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFilm.setFilm_description(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFilm.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }


}