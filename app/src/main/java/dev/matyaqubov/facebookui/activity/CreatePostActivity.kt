package dev.matyaqubov.facebookui.activity

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.URLUtil
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import dev.matyaqubov.facebookui.R
import dev.matyaqubov.facebookui.model.Post
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class CreatePostActivity : AppCompatActivity() {
    private lateinit var btn_post: Button
    private lateinit var iv_cancel_post: ImageView
    private lateinit var iv_cancel: ImageView
    private lateinit var et_post: EditText
    private lateinit var tv_title: TextView
    private lateinit var tv_website: TextView
    private lateinit var iv_post: ImageView
    private lateinit var title: String
    private lateinit var postText: String
    private lateinit var website:String
    private lateinit var imgUrl: String
    private lateinit var ll_post: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        initviews()
    }

    private fun initviews() {
        ll_post = findViewById(R.id.ll_post_photo_title)
        tv_title = findViewById(R.id.tv_title)
        tv_website = findViewById(R.id.tv_website)
        iv_post = findViewById(R.id.iv_post)
        et_post = findViewById(R.id.et_post)
        btn_post = findViewById(R.id.btn_post)
        iv_cancel_post = findViewById(R.id.iv_cancel_post)
        iv_cancel = findViewById(R.id.iv_cancel)

        et_post.addTextChangedListener {
            var post = it.toString()
            if (post!=""){
                btn_post.isEnabled = true
                checkURL(post)
            } else {
                btn_post.isEnabled=false
            }

        }

        iv_cancel.setOnClickListener {
            ll_post.visibility=View.GONE
        }


        iv_cancel_post.setOnClickListener {
            finish()
        }

        btn_post.setOnClickListener {
            postText=et_post.text.toString()
                val post=Post(R.drawable.profile_bogibek,"Matyaqubov",imgUrl,title,postText,website)
            postToFinish(post)
        }
    }

    private fun postToFinish(post: Post) {
        val intent=Intent()
        intent.putExtra("post",post)
        setResult(RESULT_OK,intent)
        finish()
    }

    @Throws(IOException::class)
    fun gettingData(url: String) {
        Thread(Runnable {
            try {
                val doc: Document =
                    Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10 * 1000).get()
                title = doc.title()
                imgUrl = doc.select("meta[property=og:image]").attr("content")
                website = doc.select("meta[property=og:url]").attr("content")
                var begin=website.indexOf("//")+2
                var end=website.indexOf("/",begin+1)
                website=website.substring(begin,end).uppercase()
                Log.d("website", "gettingData: $website")
                Log.d("imgurl", "gettingData: ${imgUrl}")
                Log.d("docim", "gettingData: ${doc.toString()}")

            } catch (e: IOException) {
                Log.d("Catch", "gettingData: $e")
                ll_post.visibility=View.GONE
            }
            runOnUiThread {
                try {
                    Glide.with(this).load(imgUrl).into(iv_post)
                    tv_title.text = title
                    tv_website.text=website
                    ll_post.visibility = View.VISIBLE
                } catch (e:Exception){
                    ll_post.visibility=View.GONE
                }
            }
        }).start()
    }
    private fun checkURL(text: String) {
        val str = text.split(" ").toTypedArray()
        for (s in str) {
            if (URLUtil.isValidUrl(s) && s.length>11) {
                gettingData(s)
                break
            } else{
                ll_post.visibility=View.GONE
            }
        }
    }

}