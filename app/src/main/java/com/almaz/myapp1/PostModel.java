package com.almaz.myapp1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//класс, сгенерированный сайтом jsonschema2pojo на основе ответа сервера.
public class PostModel {


    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("total_results")
    @Expose
    private String totalResults;
    @SerializedName("total_pages")
    @Expose
    private String totalPages;



    public String getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }





    public class Result {


        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("adult")
        @Expose
        private String adult;
        @SerializedName("overview")
        @Expose
        private String overview;
        @SerializedName("release_date")
        @Expose
        private String releaseDate;
        @SerializedName("genre_ids")
        @Expose
        private List<String> genreIds = null;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("original_title")
        @Expose
        private String originalTitle;
        @SerializedName("original_language")
        @Expose
        private String originalLanguage;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;
        @SerializedName("popularity")
        @Expose
        private String popularity;
        @SerializedName("vote_count")
        @Expose
        private String voteCount;
        @SerializedName("video")
        @Expose
        private String video;
        @SerializedName("vote_average")
        @Expose
        private String voteAverage;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
