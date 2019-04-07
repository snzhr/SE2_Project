
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.acer.oopproject.R;
import com.example.mirlan.oop_cinema.ActivityMovieInfo;
import com.example.mirlan.oop_cinema.helpers.Const;
import com.example.mirlan.oop_cinema.helpers.Movie;
import com.example.mirlan.oop_cinema.helpers.TextViewCustom;

import java.util.ArrayList;



public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolder> {

    private ArrayList<Movie> movies;

    public AdapterMovie(ArrayList<Movie> movies) {
        this.movies = movies;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvName.setText(movie.getName());
        Glide.with(holder.ivImage.getContext()).load(movie.getPoster()).into(holder.ivImage);
    }
