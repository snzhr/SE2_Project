import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.acer.oopproject.R;
import com.example.mirlan.oop_cinema.adapters.AdapterMain;
import com.example.mirlan.oop_cinema.oop.CallbackInterface;
import com.example.mirlan.oop_cinema.oop.Database;
import com.example.mirlan.oop_cinema.helpers.Genre;
import com.example.mirlan.oop_cinema.oop.RetrieveDataThread;

import java.util.ArrayList;

public class FragmentMain extends Fragment implements CallbackInterface {

    private RecyclerView genres;
    private ArrayList<Genre> movies;
    private RetrieveDataThread retrieveDataThread;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        genres = (RecyclerView) view.findViewById(R.id.recyclerView);
        genres.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        if (movies == null) {
            retrieveDataThread = new RetrieveDataThread(Database.getInstance(getContext()), this);
            retrieveDataThread.start();
        }
        else {
            setAdapters(movies);
        }
    }
 private void setAdapters(final ArrayList<Genre> movies) {
        this.movies = movies;
        genres.setAdapter(new AdapterMain(movies));
    }

    @Override
    public void dataRetrieved(final ArrayList<Genre> movies) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setAdapters(movies);
            }
        });
    }

    @Override
    public void canceled() {
        Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        if (retrieveDataThread != null)
            retrieveDataThread.interrupt();
        super.onDestroy();
    }
}
