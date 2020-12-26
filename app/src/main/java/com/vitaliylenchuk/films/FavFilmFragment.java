package com.vitaliylenchuk.films;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class FavFilmFragment extends Fragment {
    private RecyclerView mFilmRecyclerView;
    private FilmAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button mButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.film_list_fragment, container,
                false);
        mFilmRecyclerView = view.findViewById(R.id.film_recycler_view);
        //mLayoutManager = new GridLayoutManager(getActivity(), 2);
        //mFilmRecyclerView.setLayoutManager(mLayoutManager);
        updateUI();
        return view;


    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }



    private void updateUI(){
        FilmLab filmLab = FilmLab.get(getActivity());
        List<Film> films = filmLab.getFavFilms();
        if (mAdapter == null) {
            mAdapter = new FilmAdapter(films);
            mFilmRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }



    private class FilmHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private ImageView mPoster;
        private Film mFilm;
        public FilmHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_fav_film, parent, false));
            mTitle = itemView.findViewById(R.id.title);
            mPoster = itemView.findViewById(R.id.poster);
            mButton = itemView.findViewById(R.id.details);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
                    Animation.AnimationListener animationEnlargeListener = new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Intent intent = FilmActivity.newIntent(getActivity(), mFilm.getId());
                            startActivity(intent);
                            mPoster.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                    };
                    out.setAnimationListener(animationEnlargeListener);
                    mPoster.startAnimation(out);
                }
            });


        }
        public void bind(Film film){
            mFilm = film;
            mPoster.setImageResource(mFilm.getPoster());
            mTitle.setText(mFilm.getTitle());
        }
    }



    private class FilmAdapter extends RecyclerView.Adapter<FilmHolder> {
        private List<Film> mFilms;

        public FilmAdapter(List<Film> films){
            mFilms = films;
        }

        @NonNull
        @Override
        public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new FilmHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
            Film film = mFilms.get(position);
            holder.bind(film);

        }

        @Override
        public int getItemCount() {
            return mFilms.size();
        }
    }


}
