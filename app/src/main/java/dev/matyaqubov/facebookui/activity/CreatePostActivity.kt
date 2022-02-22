package dev.matyaqubov.facebookui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.widget.addTextChangedListener
import dev.matyaqubov.facebookui.R
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class CreatePostActivity : AppCompatActivity() {
    private lateinit var btn_post: Button
    private lateinit var iv_cancel_post: ImageView
    private lateinit var et_post: EditText
    private lateinit var tv_title: TextView
    private lateinit var tv_website: TextView
    private lateinit var iv_post: ImageView
    private lateinit var ll_post: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        initviews()
        gettingData("https://kun.uz/news/2022/01/02/sim-kartalarsiz-apple-raqamli-prokuror-marsda-olingan-yangi-fotosuratlar-hafta-texnologik-dayjesti")
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

        }



        iv_cancel_post.setOnClickListener {
            finish()
        }
    }

    @Throws(IOException::class)
    fun gettingData(url:String){
       Thread(Runnable {
           val stringBuilder = StringBuilder()
           try {
               val doc: Document = Jsoup.connect(url).get()
               val title: String = doc.title()
               val links: Elements = doc.select("a[href]")
               val img=doc.select("img[src~=(?i)\\\\.(png|jpe?g|gif)]")
               Log.d("dooocc", doc.toString())
               Log.d("imaggggg", img.toString())
               stringBuilder.append(title).append("\n")
               for (link in links) {
                   stringBuilder.append("\n")
                       .append("Link :")
                       .append(link.attr("href")).
                       append("\n").append("Text : ")
                       .append(link.text())
               }
           } catch (e: IOException) {
               Log.d("Catch", "gettingData: $e")
               stringBuilder.append("Error : ").append(e.message).append("\n")
           }
           runOnUiThread { tv_title.text = stringBuilder.toString() }
       }).start()
    }


}