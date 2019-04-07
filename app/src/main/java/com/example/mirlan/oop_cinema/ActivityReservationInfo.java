import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.acer.oopproject.R;
import com.example.acer.oopproject.databinding.ActivityReservationInfoBinding;
import com.example.mirlan.oop_cinema.helpers.Const;
import com.example.mirlan.oop_cinema.helpers.Movie;
import com.example.mirlan.oop_cinema.oop.Database;
import com.example.mirlan.oop_cinema.oop.User;

import java.util.ArrayList;

/**
 * Created by Mirlan on 05.12.2016.
 */
public class ActivityReservationInfo extends AppCompatActivity {

    private Movie mMovie;
    private ArrayList<Integer> seats;
    private double toPay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReservationInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation_info);
        setupActionbar();

        Intent data = getIntent();
        mMovie = (Movie) data.getSerializableExtra(Const.MOVIE);
        seats = data.getIntegerArrayListExtra(Const.USERCHOICES);
        binding.setEventHandler(this);
        binding.setMovie(mMovie);

        binding.username.setText(User.getUser().getUsername());
        binding.userCash.setText(String.valueOf(User.getUser().getMoney()));

        toPay = seats.size() * mMovie.getCost();
        String text = String.format("Количество мест %s - %s\nИтого %s", seats.size(), seats.toString(), String.valueOf(toPay));
        binding.tvSeats.setText(text);

        Glide.with(this).load(mMovie.getPoster()).into(binding.poster);
    }