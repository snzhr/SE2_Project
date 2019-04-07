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