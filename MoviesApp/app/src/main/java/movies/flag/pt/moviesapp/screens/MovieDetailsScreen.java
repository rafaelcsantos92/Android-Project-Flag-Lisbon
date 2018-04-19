package movies.flag.pt.moviesapp.screens;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import movies.flag.pt.moviesapp.R;

/**
 * Created by rafae_000 on 18/01/2018.
 */

public class MovieDetailsScreen extends Screen {


    private ImageView movieImg;
    private RatingBar ratingBar;
    private TextView infoMovie;
    private TextView nameMovie;
    private TextView dataMovie;
    private ImageButton shareMovie;
    private TextView numbersRating;
    private ImageButton backButton;
    private ImageButton homeButton;

    private final String urlMovie = "http://image.tmdb.org/t/p/w500";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_screen);

        nameMovie = findViewById(R.id.details_screen_name_movie);
        movieImg = findViewById(R.id.details_screen_img);
        ratingBar = findViewById(R.id.details_screen_rating_bar);
        infoMovie = findViewById(R.id.details_screen_info_movie);
        dataMovie = findViewById(R.id.details_screen_movie_data);
        shareMovie = findViewById(R.id.details_screen_share);
        numbersRating = findViewById(R.id.details_screen_rating_bar_numbers);
        backButton = findViewById(R.id.details_screen_back_button);
        homeButton = findViewById(R.id.details_screen_home_button);

        movieImg.setImageResource(R.drawable.ic_launcher_web);



        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailsScreen.this, MainScreen.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent movieDetails = getIntent();
        String tituloMovie = movieDetails.getStringExtra("TITULO");
        Double movieStars = movieDetails.getDoubleExtra("STARS", 0);
        String movieDescript = movieDetails.getStringExtra("DESCRIPTION");
        String movieData = movieDetails.getStringExtra("DATA");
        Double numbStars = movieDetails.getDoubleExtra("STARS",0);
        String imagemFilme = movieDetails.getStringExtra("IMG");

        final String sharSendText = getString(R.string.share_one) + " " + tituloMovie + " " + getString(R.string.share_two) + " " + numbStars + " " + getString(R.string.share_three);

        ratingBar.setRating(movieStars.floatValue());
        nameMovie.setText(tituloMovie);
        infoMovie.setText(movieDescript);
        dataMovie.setText(movieData);
        numbersRating.setText(String.valueOf(numbStars));

        DownloadImageAsyncTask asyncTask = new DownloadImageAsyncTask(movieImg);
        asyncTask.execute(urlMovie + imagemFilme);



        shareMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, sharSendText);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }



    public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView movieImage;

    private DownloadImageAsyncTask(ImageView movieImg) {
        this.movieImage = movieImg;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        return downloadImage(url);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null && movieImage != null && movieImage.getWidth() > 0) {
            Bitmap bm = Bitmap.createScaledBitmap(bitmap, movieImage.getWidth(), movieImage.getHeight(), false);
            movieImage.setImageBitmap(bm);
        }
    }
}

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }return bitmap;
    }
}
