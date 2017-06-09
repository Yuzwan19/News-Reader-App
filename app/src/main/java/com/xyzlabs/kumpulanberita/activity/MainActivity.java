package com.xyzlabs.kumpulanberita.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xyzlabs.kumpulanberita.R;
import com.xyzlabs.kumpulanberita.adapter.NewsAdapter;
import com.xyzlabs.kumpulanberita.helper.Constant;
import com.xyzlabs.kumpulanberita.model.Article;
import com.xyzlabs.kumpulanberita.model.News;
import com.xyzlabs.kumpulanberita.rest.ApiClient;
import com.xyzlabs.kumpulanberita.rest.ApiInterface;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView lv_news;
    ProgressBar progressBar;
    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_news = (ListView)findViewById(R.id.lv_news);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<News> newsCall = apiService.getArticle();
        newsCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                final List<Article> articleList = response.body().getArticles();
                String status = response.body().getStatus();
                String source = response.body().getSource();
                String sortby = response.body().getSortBy();

                NewsAdapter adapter = new NewsAdapter(MainActivity.this, articleList);
                lv_news.setAdapter(adapter);
                lv_news.setDivider(null);
                progressBar.setVisibility(View.GONE);

                lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String title = articleList.get(i).getTitle();
                        String desc = articleList.get(i).getDescription();
                        String img_url = articleList.get(i).getUrlToImage();
                        String url = articleList.get(i).getUrl();
                        String author = articleList.get(i).getAuthor();
                        String date = articleList.get(i).getPublishedAt();

                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra(Constant.INSTANCE.getKEY_TITLE(), title);
                        intent.putExtra(Constant.INSTANCE.getKEY_DESC(), desc);
                        intent.putExtra(Constant.INSTANCE.getKEY_IMG(), img_url);
                        intent.putExtra(Constant.INSTANCE.getKEY_URL(), url);
                        intent.putExtra(Constant.INSTANCE.getKEY_AUTHOR(), author);
                        intent.putExtra(Constant.INSTANCE.getKEY_DATE(), date);

                        startActivityForResult(intent,0);
                    }
                });

                Log.d("STATUS", response.toString());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.e(TAG, "onFailure: No Data to display", t);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
