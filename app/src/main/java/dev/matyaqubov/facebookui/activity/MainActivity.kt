package dev.matyaqubov.facebookui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.matyaqubov.facebookui.R
import dev.matyaqubov.facebookui.adapter.FeedAdapter
import dev.matyaqubov.facebookui.model.Feed
import dev.matyaqubov.facebookui.model.Post
import dev.matyaqubov.facebookui.model.Story

class MainActivity : AppCompatActivity() {
private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.layoutManager=GridLayoutManager(this,1)
        refreshAdapter(getAllFeeds())
    }

    private fun refreshAdapter(feeds: ArrayList<Feed>) {
        val adapter=FeedAdapter(this,feeds)
        recyclerView.adapter=adapter
    }

    private fun getAllFeeds(): ArrayList<Feed> {
        val stories=ArrayList<Story>()
        stories.add(Story(R.drawable.profile_azizbek,"Azizbek"))
        stories.add(Story(R.drawable.profile_bogibek,"Matyaqubov"))
        stories.add(Story(R.drawable.profile_mine,"Jonibek"))
        stories.add(Story(R.drawable.profile_nazirov,"Nazirov"))
        stories.add(Story(R.drawable.profile_ogabek,"OgabekDev"))
        stories.add(Story(R.drawable.profile_saidahmad,"Saidahmad"))
        stories.add(Story(R.drawable.profile_samandar,"Samandar"))

        val feeds=ArrayList<Feed>()
        //head
        feeds.add(Feed())
        //story
        feeds.add(Feed(stories))
        //Post

        //1
        feeds.add(Feed(Post(R.drawable.my_image,"Bogibek", arrayListOf(R.drawable.post_image_1))))
        //2
        feeds.add(Feed(Post(R.drawable.profile_ogabek,"Ogabek Dev", arrayListOf(R.drawable.post_image_1,R.drawable.post_image_1))))
        //3
        feeds.add(Feed(Post(R.drawable.profile_mine,"Jonibek", arrayListOf(R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1))))
        //4
        feeds.add(Feed(Post(R.drawable.profile_saidahmad,"Saidahmad", arrayListOf(R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1))))
        //5
        feeds.add(Feed(Post(R.drawable.profile_samandar,"Samandar", arrayListOf(R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1))))
        //6
        feeds.add(Feed(Post(R.drawable.profile_bogibek,"Matyaqubov", arrayListOf(R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1))))
        //8
        feeds.add(Feed(Post(R.drawable.profile_azizbek,"Azizbek", arrayListOf(R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1,R.drawable.post_image_1))))


        return feeds
    }

}