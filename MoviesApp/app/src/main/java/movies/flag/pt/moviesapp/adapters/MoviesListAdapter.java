package movies.flag.pt.moviesapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.screens.MovieDetailsScreen;

/**
 * Created by rafae_000 on 18/01/2018.
 */

public class MoviesListAdapter extends BaseAdapter {


    private ArrayList<Movie> moviesList;
    private Context context;
    private final String urlMovie = "http://image.tmdb.org/t/p/w500";


    public MoviesListAdapter(ArrayList<Movie> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return moviesList.size();
    }

    @Override
    public Movie getItem(int position) {
        return moviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.label = view.findViewById(R.id.list_item_label);
            viewHolder.relativeLayout = view.findViewById(R.id.list_item_list_item);
            viewHolder.img = view.findViewById(R.id.list_item_img);
            viewHolder.stars = view.findViewById(R.id.list_item_stars);
            viewHolder.numbStars = view.findViewById(R.id.list_item_stars_numbers);
            viewHolder.imdbAval = view.findViewById(R.id.list_item_rating_text);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        Movie item = getItem(position);
        viewHolder.label.setText(item.getTitle());
        viewHolder.imdbAval.setText(R.string.imdb_av);
        viewHolder.numbStars.setText(String.valueOf(item.getVoteAverage()));
        viewHolder.stars.setRating(item.getVoteAverage().floatValue());
        viewHolder.img.setImageResource(R.drawable.ic_launcher_web);
        if (item.getPosterPath() != null && item.getPosterPath().length() > 0) {
            DownloadImageAsyncTask downloadImageAsyncTask = new DownloadImageAsyncTask(viewHolder.img);
            downloadImageAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,urlMovie + item.getPosterPath());
        }

        final String title = item.getTitle();
        final Double ratingStars = item.getVoteAverage();
        final String movieDescription = item.getOverview();
        final String movieData = item.getReleaseDate();
        final String img = item.getBackdropPath();



        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovieDetailsScreen.class);
                intent.putExtra("TITULO",title);
                intent.putExtra("STARS",ratingStars);
                intent.putExtra("DESCRIPTION",movieDescription);
                intent.putExtra("DATA", movieData);
                intent.putExtra("IMG", img);
                context.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHolder {
        RelativeLayout relativeLayout;
        TextView label;
        ImageView img;
        RatingBar stars;
        TextView numbStars;
        TextView imdbAval;
    }

    public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView img;

        public DownloadImageAsyncTask(ImageView img) {
            this.img = img;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            return downloadImage(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null && img != null && img.getWidth() > 0) {
                Bitmap bm = Bitmap.createScaledBitmap(bitmap, img.getWidth(), img.getHeight(), false);
                img.setImageBitmap(bm);
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
