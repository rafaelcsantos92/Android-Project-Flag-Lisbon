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
 * Created by rafae_000 on 29/01/2018.
 */

public class TvPopularDetailsScreen extends Screen {

    private ImageView serieImg;
    private RatingBar ratingBar;
    private TextView infoSerie;
    private TextView nameSerie;
    private TextView dataSerie;
    private ImageView shareSerie;
    private TextView numbersRating;
    private ImageButton backButton;
    private ImageButton homeButton;


    private final String urlSerie = "http://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_screen);

        nameSerie = findViewById(R.id.details_screen_name_movie);
        serieImg = findViewById(R.id.details_screen_img);
        ratingBar = findViewById(R.id.details_screen_rating_bar);
        infoSerie = findViewById(R.id.details_screen_info_movie);
        dataSerie = findViewById(R.id.details_screen_movie_data);
        shareSerie = findViewById(R.id.details_screen_share);
        numbersRating = findViewById(R.id.details_screen_rating_bar_numbers);
        backButton = findViewById(R.id.details_screen_back_button);
        homeButton = findViewById(R.id.details_screen_home_button);


        serieImg.setImageResource(R.drawable.ic_launcher_web);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TvPopularDetailsScreen.this, MainScreen.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent serieDetails = getIntent();
        String tituloSerie = serieDetails.getStringExtra("TITULO");
        Double serieStars = serieDetails.getDoubleExtra("STARS", 0);
        String serieDescript = serieDetails.getStringExtra("DESCRIPTION");
        String serieData = serieDetails.getStringExtra("DATA");
        Double numbStars = serieDetails.getDoubleExtra("STARS",0);
        String imagemSerie = serieDetails.getStringExtra("IMG");

        final String shareSendText = getString(R.string.share_one_serie) + " " + tituloSerie + " " +  getString(R.string.share_two) + " " + numbStars + " " + getString(R.string.share_three);

        ratingBar.setRating(serieStars.floatValue());
        nameSerie.setText(tituloSerie);
        infoSerie.setText(serieDescript);
        dataSerie.setText(serieData);
        numbersRating.setText(String.valueOf(numbStars));

        DownloadImageAsyncTask asyncTask = new DownloadImageAsyncTask(serieImg);
        asyncTask.execute(urlSerie + imagemSerie);

        shareSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, shareSendText);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

    }


    public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView serieImage;

        private DownloadImageAsyncTask(ImageView movieImg) {
            this.serieImage = movieImg;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            return downloadImage(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null && serieImage != null && serieImage.getWidth() > 0) {
                Bitmap bm = Bitmap.createScaledBitmap(bitmap, serieImage.getWidth(), serieImage.getHeight(), false);
                serieImage.setImageBitmap(bm);
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
