package com.almaz.myapp1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CollFilm {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
}
