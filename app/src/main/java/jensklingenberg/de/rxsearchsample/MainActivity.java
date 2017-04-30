package jensklingenberg.de.rxsearchsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.widget.RxTextView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  RecyclerAdapter adapter;
  @BindView(R.id.rv_podcast) RecyclerView rv_podcast;
  @BindView(R.id.editText) EditText editText;
  List<String> movieList = new ArrayList();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    adapter = new RecyclerAdapter(MainActivity.this);

    LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
    rv_podcast.setLayoutManager(gridLayoutManager);

    rv_podcast.setAdapter(adapter);

    movieList.add("matrix");
    movieList.add("halo");
    movieList.add("8Mile");
    movieList.add("Things I Hate About You");
    movieList.add("12 Angry Men");
    movieList.add("12 Years a Slave");
    movieList.add("28 Days Later");
    movieList.add("28 Weeks Later");
    movieList.add("50 First Dates");
    movieList.add("2001: A Space Odyssey");

    RxTextView.textChanges(editText) //j
        .map(charSequence -> charSequence.toString().toLowerCase())
        .subscribe(query -> {
          adapter.clear();

          Observable.just(movieList)
              .flatMapIterable(x -> x)
              .filter(movieTitle -> movieTitle.contains(query))
              .subscribe(s1 -> adapter.addItem(s1));
        });
  }
}
