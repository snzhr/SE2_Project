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
    @Override
    public int getItemCount() {
        return SEATS;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSeat;

        ViewHolder(View itemView) {
            super(itemView);
            tvSeat = (TextView) itemView;
            tvSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer position = getAdapterPosition() + 1;
                    if (userChoices.contains(position)) {
                        view.setBackgroundResource(R.drawable.seat_free);
                        userChoices.remove(position);
                    } else if (reservedSeats.contains(position))
                        Toast.makeText(view.getContext(), "Reserved already", Toast.LENGTH_SHORT).show();
                    else if (userChoices.size() >= 8)
                        Toast.makeText(view.getContext(), "Can't reserve > 8", Toast.LENGTH_SHORT).show();
                    else {
                        view.setBackgroundResource(R.drawable.seat_choice);
                        userChoices.add(position);
                    }
                }
            });
        }
    }
}
