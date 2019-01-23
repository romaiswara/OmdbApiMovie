package com.example.roma.omdbapitest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roma.omdbapitest.R;
import com.example.roma.omdbapitest.adapter.MovieAdapter;
import com.example.roma.omdbapitest.database.AppDb;
import com.example.roma.omdbapitest.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;

    List<Movie> movieList=new ArrayList<>();
    MovieAdapter adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, v);

        setAdapter(AppDb.getDatabase(getActivity()).movie().getAllMovie());

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setAdapter(AppDb.getDatabase(getActivity()).movie().getAllMovie());
        adapter.notifyDataSetChanged();
    }

    private void setAdapter(List<Movie> movies){
        adapter = new MovieAdapter(getActivity(), movies);
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovie.setAdapter(adapter);
    }
}
