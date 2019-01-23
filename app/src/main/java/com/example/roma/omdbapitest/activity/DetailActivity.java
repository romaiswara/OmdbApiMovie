package com.example.roma.omdbapitest.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roma.omdbapitest.R;
import com.example.roma.omdbapitest.database.AppDb;
import com.example.roma.omdbapitest.model.Movie;
import com.example.roma.omdbapitest.rest.ApiClient;
import com.example.roma.omdbapitest.rest.MovieService;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.imgPoster)
    ImageView imgPoster;
    @BindView(R.id.tvJudul)
    TextView tvJudul;
    @BindView(R.id.tvRelease)
    TextView tvRelease;
    @BindView(R.id.tvGenre)
    TextView tvGenre;
    @BindView(R.id.tvActor)
    TextView tvActor;
    @BindView(R.id.fabFav)
    FloatingActionButton fabFav;

    private String idMovie;

    MovieService movieService;
    AppDb db;
    Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        idMovie = getIntent().getStringExtra("id");
        db = AppDb.getDatabase(this);
        checkFav();

        movieService = ApiClient.getRetrofit().create(MovieService.class);
        Call<Movie> call = movieService.detailMovie(idMovie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    Movie movie = response.body();
                    tvJudul.setText(movie.getTitle());
                    tvRelease.setText(movie.getReleased());
                    tvGenre.setText(movie.getGenre());
                    tvActor.setText(movie.getActors());
                    Picasso.get().load(movie.getPoster()).placeholder(R.drawable.no_image).fit().into(imgPoster);

                    mMovie = movie;

                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

        fabFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.movie().getById(idMovie) == null){
                    Toast.makeText(DetailActivity.this, "Favorite", Toast.LENGTH_SHORT).show();
                    db.movie().insertMovie(mMovie);
                } else {
                    Toast.makeText(DetailActivity.this, "Unfavorite", Toast.LENGTH_SHORT).show();
                    db.movie().deleteById(idMovie);
                }
                checkFav();
            }
        });
    }

    private void checkFav(){
        if (db.movie().getById(idMovie) == null){
            fabFav.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_white));
        } else {
            fabFav.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_white));
        }
    }
}
