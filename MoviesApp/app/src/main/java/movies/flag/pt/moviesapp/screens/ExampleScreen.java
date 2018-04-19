package movies.flag.pt.moviesapp.screens;

import android.os.Bundle;

import java.util.List;

import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;
import movies.flag.pt.moviesapp.http.requests.GetNowPlayingMoviesAsyncTask;
import movies.flag.pt.moviesapp.utils.DLog;

public class ExampleScreen extends Screen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeRequestExample();
    }

    private void executeRequestExample() {
        // Example to request get now playing movies
        GetNowPlayingMoviesAsyncTask asyncTask = new GetNowPlayingMoviesAsyncTask(this, 1) {

            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                DLog.d(tag, "onResponseSuccess " + moviesResponse);
                // Here i can create the adapter :)
                List<Movie> movies = moviesResponse.getMovies();
            }

            @Override
            protected void onNetworkError() {
                DLog.d(tag, "onNetworkError ");
                // Here i now that some error occur when processing the request,
                // possible my internet connection is turned off
            }
        };
        asyncTask.execute();
    }
}
