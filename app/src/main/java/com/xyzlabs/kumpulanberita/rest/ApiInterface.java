package com.xyzlabs.kumpulanberita.rest;

import com.xyzlabs.kumpulanberita.model.Article;
import com.xyzlabs.kumpulanberita.model.News;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 04/06/2017.
 */

public interface ApiInterface {
    @GET("news.json")
    Call<News> getArticle();

}
