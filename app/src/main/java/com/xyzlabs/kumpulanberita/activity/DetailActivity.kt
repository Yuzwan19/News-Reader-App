package com.xyzlabs.kumpulanberita.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso
import com.xyzlabs.kumpulanberita.R
import com.xyzlabs.kumpulanberita.helper.Constant
import com.xyzlabs.kumpulanberita.model.Article
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

//        txt_desc = findViewById(R.id.txt_desc) as TextView
//        txt_title_detail = findViewById(R.id.txt_title_detail) as TextView
//        img_detail = findViewById(R.id.img_detail) as ImageView


        val intent = intent
        var URL = intent.getStringExtra(Constant.KEY_URL)
//        txt_desc.text = intent.getStringExtra(Constant.KEY_DESC)
        txt_desc_detail.text = intent.getStringExtra(Constant.KEY_DESC)

        Picasso.with(this).load(intent.getStringExtra(Constant.KEY_IMG)).into(img_detail)
        txt_title_detail.text = intent.getStringExtra(Constant.KEY_TITLE)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(android.content.Intent.ACTION_SEND)
            intent.type = "text/html"
            //                intent.putExtra(Intent.EXTRA_SUBJECT, "sample");
            intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(URL))
            startActivity(Intent.createChooser(intent, "Share"))
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
