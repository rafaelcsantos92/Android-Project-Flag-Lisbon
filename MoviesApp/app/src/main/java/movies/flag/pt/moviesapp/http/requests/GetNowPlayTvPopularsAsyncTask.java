package movies.flag.pt.moviesapp.http.requests;

import android.content.Context;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.TvPopularResponse;

/**
 * Created by rafae_000 on 23/01/2018.
 */

public abstract class GetNowPlayTvPopularsAsyncTask extends ExecuteRequestAsyncTask<TvPopularResponse> {

    private static final String PATH = "/tv/popular";
    private static final String LANGUAGE_KEY = "language";
    private static final String PAGE = "page";
    private int page;

    public GetNowPlayTvPopularsAsyncTask(Context context, int page) {
        super(context);
        this.page = page;
        this.context = context;
    }


    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected void addQueryParams(StringBuilder sb) {
        addQueryParam(sb, LANGUAGE_KEY, context.getString(R.string.language_value));
        addQueryParam(sb,PAGE,String.valueOf(page));

    }

    @Override
    protected Class<TvPopularResponse> getResponseEntityClass() {
        return TvPopularResponse.class;
    }
}
