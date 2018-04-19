package movies.flag.pt.moviesapp.http.entities;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rafae_000 on 23/01/2018.
 */

public class TvPopularResponse {

    @SerializedName("page")
    private Integer page;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("results")
    private ArrayList<TvPopular> tvPopulars = new ArrayList<>();

    public Integer getPage() {
        return page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public ArrayList<TvPopular> getTvPopulars() {
        return tvPopulars;
    }

}
