import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.oopproject.R;

import java.util.ArrayList;



public class AdapterSeats extends RecyclerView.Adapter<AdapterSeats.ViewHolder> {

    private int SEATS = 81;
    private ArrayList<Integer> reservedSeats, userChoices;

    public AdapterSeats(ArrayList<Integer> mSeats, ArrayList<Integer> userChoices) {
        this.reservedSeats = mSeats;
        this.userChoices = userChoices;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Integer pos = position + 1;
        if (reservedSeats.contains(pos)) {
            holder.tvSeat.setBackgroundResource(R.drawable.seat_reserved);
        } else if (userChoices.contains(pos))
            holder.tvSeat.setBackgroundResource(R.drawable.seat_choice);

        holder.tvSeat.setText(String.valueOf(pos));
    }
