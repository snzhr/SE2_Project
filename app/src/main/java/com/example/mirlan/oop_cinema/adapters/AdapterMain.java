import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.oopproject.R;
import com.example.mirlan.oop_cinema.helpers.TextViewCustom;
import com.example.mirlan.oop_cinema.helpers.Genre;

import java.util.ArrayList;



public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {

    private ArrayList<Genre> genres;

    public AdapterMain(ArrayList<Genre> list) {
        genres = list;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Genre genre = genres.get(position);
        holder.tvGenreName.setText(genre.getName());
        holder.genreList.setLayoutManager(new LinearLayoutManager(holder.genreList.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.genreList.setAdapter(new AdapterMovie(genre.getMovieList()));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextViewCustom tvGenreName;
        private RecyclerView genreList;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGenreName = (TextViewCustom) itemView.findViewById(R.id.itemGenreName);
            genreList = (RecyclerView) itemView.findViewById(R.id.itemGenreList);
        }
    }
}
