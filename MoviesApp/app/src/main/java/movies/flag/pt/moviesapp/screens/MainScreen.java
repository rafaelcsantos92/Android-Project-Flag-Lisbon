package movies.flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import movies.flag.pt.moviesapp.R;


/**
 * Created by rafae_000 on 28/01/2018.
 */

public class MainScreen extends Screen {

    Button moviesBtn;
    Button seriesBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        moviesBtn = findViewById(R.id.main_screen_movies_button);
        seriesBtn = findViewById(R.id.main_screen_series_button);

        moviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, MoviesScreen.class);
                startActivity(intent);
            }
        });

        seriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, TvPopularScreen.class);
                startActivity(intent);
            }
        });

    }
}
