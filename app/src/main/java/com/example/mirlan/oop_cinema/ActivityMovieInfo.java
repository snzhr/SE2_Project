import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.oopproject.R;
import com.example.mirlan.oop_cinema.helpers.Const;
import com.example.mirlan.oop_cinema.helpers.TextViewCustom;
import com.example.mirlan.oop_cinema.oop.Database;
import com.example.mirlan.oop_cinema.helpers.Movie;
import com.example.mirlan.oop_cinema.oop.User;
import com.example.mirlan.oop_cinema.oop.UserAnonymous;

public class ActivityMovieInfo extends AppCompatActivity implements View.OnClickListener {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        setupActionbar();

        movie = (Movie) getIntent().getSerializableExtra(Const.MOVIE);

        ImageView imageView = (ImageView) findViewById(R.id.ivImage);
        Glide.with(this).load(movie.getPoster()).into(imageView);

        ((TextViewCustom) findViewById(R.id.tvTitle)).setText(movie.getName());
        ((TextViewCustom) findViewById(R.id.tvSlogan)).setText(movie.getSlogan());
        ((TextViewCustom) findViewById(R.id.tvDescription)).setText(movie.getDescription());
        ((TextView) findViewById(R.id.tvCost)).setText(String.valueOf(movie.getCost() + " сом"));

        findViewById(R.id.order).setOnClickListener(this);
    }
     @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.order) {
            Intent intent;
            if (User.getUser() instanceof UserAnonymous) {
                intent = new Intent(this, ActivitySignIn.class);
                intent.putExtra(Const.STARTRESACTIVITY, true);
            }
            else intent = new Intent(this, ActivitySeatReservation.class);
            intent.putExtra(Const.MOVIE, movie);
            startActivity(intent);
        }
    }

