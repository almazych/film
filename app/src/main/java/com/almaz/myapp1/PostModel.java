package com.almaz.myapp1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

}