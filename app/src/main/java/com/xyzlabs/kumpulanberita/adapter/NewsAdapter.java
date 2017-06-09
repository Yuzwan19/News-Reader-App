package com.xyzlabs.kumpulanberita.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xyzlabs.kumpulanberita.R;
import com.xyzlabs.kumpulanberita.model.Article;

import java.util.List;

/**
 * Created by user on 04/06/2017.
 */

public class NewsAdapter extends BaseAdapter{
    // params
    List<Article> listItem;
    Activity activity;

    public NewsAdapter(Activity activity, List<Article> listItem){
        this.activity = activity;
        this.listItem = listItem;
    }

    //method ini akan menentukan seberapa banyak data yang akan ditampilkan didalam ListView
    //listItem.size() == jumlah data dalam object List yang ada
    @Override
    public int getCount() {
        return listItem.size();
    }

    //method ini untuk mengakses per-item objek dalam list
    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //method ini yang akan menampilkan baris per baris dari item yang ada di ListView
    //dengan menggunakan pattern ViewHolder untuk optimasi performa dari ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list, null);
            holder.txtTitle = (TextView)view.findViewById(R.id.txt_title);
            holder.txtDate = (TextView)view.findViewById(R.id.txt_date);
            holder.txtAuthor = (TextView)view.findViewById(R.id.txt_author);
            holder.txtDesc = (TextView)view.findViewById(R.id.txt_desc);
            holder.imgItem = (ImageView)view.findViewById(R.id.img_title);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        Article article = (Article) getItem(position);
        holder.txtTitle.setText(article.getTitle());
        holder.txtDate.setText(article.getPublishedAt());
        holder.txtAuthor.setText(article.getAuthor());
        holder.txtDesc.setText(article.getDescription());

        Picasso.with(activity).load(article.getUrlToImage()).into(holder.imgItem);

        return view;
    }

    static class ViewHolder{
        ImageView imgItem;
        TextView txtTitle, txtDesc, txtDate, txtAuthor;
    }
}
