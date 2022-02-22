package dev.matyaqubov.facebookui.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.webkit.URLUtil
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import dev.matyaqubov.facebookui.R
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import kotlin.math.log

class CreatePostActivity : AppCompatActivity() {
    private lateinit var btn_post: Button
    private lateinit var iv_cancel_post: ImageView
    private lateinit var et_post: EditText
    private lateinit var tv_title: TextView
    private lateinit var tv_website: TextView
    private lateinit var iv_post: ImageView
    private lateinit var title: String
    private lateinit var imgUrl: String
    private lateinit var ll_post: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        initviews()
       // gettingData("https://pdp.uz")
    }

    private fun initviews() {
        ll_post = findViewById(R.id.ll_post_photo_title)
        tv_title = findViewById(R.id.tv_title)
        tv_website = findViewById(R.id.tv_website)
        iv_post = findViewById(R.id.iv_post)
        et_post = findViewById(R.id.et_post)
        btn_post = findViewById(R.id.btn_post)
        iv_cancel_post = findViewById(R.id.iv_cancel_post)

        et_post.addTextChangedListener {
            btn_post.isEnabled = true
            var post = it.toString()
            checkURL(post)

        }



        iv_cancel_post.setOnClickListener {
            finish()
        }
    }

    @Throws(IOException::class)
    fun gettingData(url: String) {
        Thread(Runnable {
            try {
                val doc: Document =
                    Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10 * 1000).get()
                title = doc.title()
                imgUrl = doc.select("meta[property=og:image]").attr("content")
                Log.d("imgurl", "gettingData: ${imgUrl}")
                Log.d("docim", "gettingData: ${doc.toString()}")

            } catch (e: IOException) {
                Log.d("Catch", "gettingData: $e")
            }
            runOnUiThread {
                try {
                    Glide.with(this).load(imgUrl).into(iv_post)
                    ll_post.visibility = View.VISIBLE
                    tv_title.text = title
                } catch (e:Exception){

                }
            }
        }).start()
    }
    private fun checkURL(text: String) {
        val spannable: Spannable = SpannableString(text)
        val str = text.split(" ").toTypedArray()
        for (s in str) {
            if (URLUtil.isValidUrl(s) && s.length>11) {
                gettingData(s)
                break
            } else{
                ll_post.visibility=View.GONE
            }
        }
//        val textViewUrl = findViewById<TextView>(R.id.tv_link)
//        textViewUrl.movementMethod = LinkMovementMethod()
//        textViewUrl.text = spannable
    }

}