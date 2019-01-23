package com.example.roma.omdbapitest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.omdbapitest.R;
import com.example.roma.omdbapitest.activity.DetailActivity;
import com.example.roma.omdbapitest.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void updateData(List<Movie> movies){
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_item, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Movie movie = movieList.get(i);
        myViewHolder.bindItem(movie);
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("id", movie.getImdbID()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvJudul)
        TextView tvJudul;
        @BindView(R.id.tvYear)
        TextView tvYear;
        @BindView(R.id.tvType)
        TextView tvType;
        @BindView(R.id.ivPoster)
        ImageView ivPoster;
        @BindView(R.id.cardView)
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(Movie movie){
            tvJudul.setText(movie.getTitle());
            tvYear.setText("Year: "+movie.getYear());
            tvType.setText("Type: "+movie.getType());
            Picasso.get().load(movie.getPoster()).placeholder(R.drawable.no_image).fit().into(ivPoster);
        }
    }

//    private Context context;
//    private List<Movie> items;
//
//    public MovieAdapter(Context context, List<Movie> items) {
//        this.context = context;
//        this.items = items;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.movie_item, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        final Movie movie = items.get(position);
//        holder.bindItem(movie);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context, DetailActivity.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        .putExtra("id", movie.getImdbID()));
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.tvJudul)
//        TextView tvJudul;
//        @BindView(R.id.tvYear)
//        TextView tvYear;
//        @BindView(R.id.tvType)
//        TextView tvType;
//        @BindView(R.id.ivPoster)
//        ImageView ivPoster;
//        @BindView(R.id.cardView)
//        CardView cardView;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//
//        public void bindItem(Movie movie){
//            tvJudul.setText(movie.getTitle());
//            tvYear.setText("Year: "+movie.getYear());
//            tvType.setText("Type: "+movie.getType());
//            Picasso.get().load(movie.getPoster()).placeholder(R.drawable.no_image).fit().into(ivPoster);
//        }
//    }
}
