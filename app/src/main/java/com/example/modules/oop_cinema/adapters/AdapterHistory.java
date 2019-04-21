import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.oopproject.R;
import com.example.mirlan.oop_cinema.helpers.Movie;
import com.example.mirlan.oop_cinema.helpers.Reservation;
import com.example.mirlan.oop_cinema.oop.Database;
import com.example.mirlan.oop_cinema.oop.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<Reservation> reservations;
    private SimpleDateFormat format;

    public AdapterHistory(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        reservations = Database.getInstance(context).getReservationHistory(User.getUser().getUsername());
        format = new SimpleDateFormat("MMM d HH:mm:ss", Locale.US);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_history, parent, false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        Movie movie = reservation.getMovie();

        holder.tvMovieName.setText(movie.getName());
        holder.tvDescription.setText(movie.getDescription());
        holder.tvReservAmt.setText(String.format("Количество мест %s - %s",
                String.valueOf(reservation.getSeatAmt()), reservation.getSeatNums()));

        holder.tvReservCost.setText(String.format("Итого %s",
                String.valueOf(movie.getCost() * reservation.getSeatAmt())));

        holder.tvTimestamp.setText(format.format(new Date(reservation.getTimestamp())));

        Glide.with(context).load(movie.getPoster()).into(holder.poster);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        Log.e("TAG", "position being released --> " + holder.getAdapterPosition());
        Glide.clear(holder.poster);
        super.onViewRecycled(holder);
    }
  @Override
    public int getItemCount() {
        return reservations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView poster;
        private TextView tvMovieName, tvDescription, tvReservAmt, tvReservCost, tvTimestamp;

        ViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            tvMovieName = (TextView) itemView.findViewById(R.id.movieName);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvReservAmt = (TextView) itemView.findViewById(R.id.tvReservationAmt);
            tvReservCost = (TextView) itemView.findViewById(R.id.tvReservationCost);
            tvTimestamp = (TextView) itemView.findViewById(R.id.timestamp);
            itemView.findViewById(R.id.aboutMovie).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
