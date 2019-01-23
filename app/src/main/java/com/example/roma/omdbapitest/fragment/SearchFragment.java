package com.example.roma.omdbapitest.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.roma.omdbapitest.R;
import com.example.roma.omdbapitest.adapter.MovieAdapter;
import com.example.roma.omdbapitest.model.Movie;
import com.example.roma.omdbapitest.model.MovieResponse;
import com.example.roma.omdbapitest.rest.ApiClient;
import com.example.roma.omdbapitest.rest.MovieService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    @BindView(R.id.etJudul)
    EditText etJudul;
    @BindView(R.id.etTahun)
    EditText etTahun;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    MovieService movieService;
    MovieAdapter adapter;

    List<Movie> movieList=new ArrayList<>();

    LinearLayoutManager llm;

    boolean isScrolling = false;
    int currentItem, totalItem, scrollOutItem, totalAllItem, page;
    String judul, tahun, type;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, v);

        movieService = ApiClient.getRetrofit().create(MovieService.class);

        adapter = new MovieAdapter(getActivity(), movieList);
        llm = new LinearLayoutManager(getActivity());
        rvMovie.setLayoutManager(llm);
        rvMovie.setAdapter(adapter);
        rvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = llm.getChildCount();
                totalItem = llm.getItemCount();
                scrollOutItem = llm.findFirstVisibleItemPosition();

                if (isScrolling && (currentItem+scrollOutItem == totalItem)){
                    isScrolling = false;
                    if (totalItem < totalAllItem){
                        page++;
                        getData(judul,tahun,type, page);
                    }
                }
            }
        });
        progressBar.setVisibility(View.GONE);

        return v;
    }

    @OnClick(R.id.btnSearch)
    public void btnSearch(){
        movieList.clear();
        judul = etJudul.getText().toString();
        tahun = etTahun.getText().toString();
        type = spinner.getSelectedItem().toString();
        page = 1;

        if (judul.isEmpty()){
            Toast.makeText(getActivity(), "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        getData(judul, tahun, type, page);

    }

    private void getData(String key, String tahun, String type, int page) {
        progressBar.setVisibility(View.VISIBLE);
        Call<MovieResponse> call;
        if (page == 1 ){
            call = movieService.searchMovie(key, tahun, type);
        } else {
            call = movieService.searchMovie(key, tahun, type, page);
        }
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    if (response.body().isResponse()){
                        totalAllItem = response.body().getTotalResult();
                        movieList.addAll(response.body().getMovieList());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "total "+response.body().getTotalResult(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

}
