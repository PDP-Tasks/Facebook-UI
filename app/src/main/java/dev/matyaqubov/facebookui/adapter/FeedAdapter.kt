package dev.matyaqubov.facebookui.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import dev.matyaqubov.facebookui.R
import dev.matyaqubov.facebookui.R.color.background
import dev.matyaqubov.facebookui.helper.MyCLickListener
import dev.matyaqubov.facebookui.model.Feed
import dev.matyaqubov.facebookui.model.Story

class FeedAdapter(var context: Context, var items: ArrayList<Feed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var myClick: (() -> Unit)? = null

    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2
    private val TYPE_ITEM_POST_LINK = 3

    override fun getItemViewType(position: Int): Int {
        var feed = items[position]
        if (feed.isHeader) return TYPE_ITEM_HEAD
        else if (feed.stories.size > 0) return TYPE_ITEM_STORY
        else if (items[position].post!!.website != "") {
            return TYPE_ITEM_POST_LINK
        } else {
            return TYPE_ITEM_POST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_HEAD) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_header, parent, false)
            return HeadViewHolder(view)
        } else if (viewType == TYPE_ITEM_STORY) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false)
            return StoryViewHolder(context, view)
        } else if (viewType == TYPE_ITEM_POST_LINK) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_feed_post_link, parent, false)
            return PostLinkViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post, parent, false)
            return PostViewHolder(view)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = items[position]

        if (holder is HeadViewHolder) {
            holder.tv_mind.setOnClickListener {
                myClick?.invoke()
            }

        }

        if (holder is StoryViewHolder) {

            holder.apply {
                if (adapter == null){
                    adapter = StoryAdapter(context, feed.stories)
                    recyclerView.adapter = adapter
                }

            }

        }

        if (holder is PostViewHolder) {
            holder.iv_profile.setImageResource(feed.post!!.profile)
            holder.tv_fullname.text = feed.post!!.fullname
            val ll = holder.ll_photo_list1
            ll.removeAllViews()
            ll.addView(createItems(context, feed.post!!.photos))

        }

        if (holder is PostLinkViewHolder) {
            holder.iv_profile.setImageResource(feed.post!!.profile)
            holder.tv_fullname.text = feed.post!!.fullname
            Glide.with(context).load(feed.post!!.photo).into(holder.iv_post)
            holder.tv_title.text = feed.post!!.title
            holder.tv_post.text = feed.post!!.post
            holder.tv_website.text = feed.post!!.website
        }

    }

    private fun createItems(context: Context, photos: ArrayList<Int>): LinearLayout {
        Log.d("createItems: ", "photos: ${photos.size} ")
        val all = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            val parms = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            parms.setMargins(0, 0, 0, 0)
            layoutParams = parms
        }

        when (photos.size) {
            1 -> {
                val imageView = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[0])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }
                all.addView(imageView)
            }
            2 -> {
                val tempLinearLayout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    parms.setMargins(0, 0, 0, 0)
                    layoutParams = parms
                }
                val imageView = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[0])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView2 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[1])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }
                tempLinearLayout.addView(imageView)
                tempLinearLayout.addView(imageView2)
                return tempLinearLayout
            }

            3 -> {
                val tempLinearLayout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    parms.setMargins(0, 0, 0, 0)
                    layoutParams = parms
                }
                val imageView = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[0])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView2 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[1])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }
                tempLinearLayout.addView(imageView)
                tempLinearLayout.addView(imageView2)

                val templl = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    parms.setMargins(0, 0, 0, 0)
                    layoutParams = parms
                }
                val imageView3 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[2])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                templl.addView(imageView3)
                all.addView(tempLinearLayout)
                all.addView(templl)
            }
            4 -> {
                val tempLinearLayout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    parms.setMargins(0, 0, 0, 0)
                    layoutParams = parms
                }
                val imageView = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[0])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView2 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[1])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }
                tempLinearLayout.addView(imageView)
                tempLinearLayout.addView(imageView2)

                val templl = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    parms.setMargins(0, 0, 0, 0)
                    layoutParams = parms
                }
                val imageView3 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[2])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView4 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[2])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    layoutParams = params

                }

                templl.addView(imageView3)
                templl.addView(imageView4)
                all.addView(tempLinearLayout)
                all.addView(templl)
            }

            5 -> {
                val tempLinearLayout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    parms.setMargins(0, 0, 0, 0)
                    layoutParams = parms
                }
                val imageView = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[0])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView2 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[1])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }
                tempLinearLayout.addView(imageView)
                tempLinearLayout.addView(imageView2)

                val templl = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    parms.setMargins(0, 0, 0, 0)
                    layoutParams = parms
                }
                val imageView3 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[2])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView4 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[2])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView5 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[2])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                templl.addView(imageView3)
                templl.addView(imageView4)
                templl.addView(imageView5)
                all.addView(tempLinearLayout)
                all.addView(templl)
            }

            else -> {

                val tempLinearLayout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    parms.setMargins(0, 0, 0, 0)
                    layoutParams = parms
                }
                val imageView = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[0])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView2 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[1])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }
                tempLinearLayout.addView(imageView)
                tempLinearLayout.addView(imageView2)

                val templl = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    parms.setMargins(0, 0, 0, 0)
                    layoutParams = parms
                }
                val imageView3 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[2])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView4 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[2])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val imageView5 = ImageView(context).apply {
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                    )
                    setImageResource(photos[2])
                    params.setMargins(5, 5, 5, 5)
                    adjustViewBounds = true
                    layoutParams = params

                }

                val frameLayout = FrameLayout(context).apply {
                    val parms = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                    parms.setMargins(0, 0, 0, 0)

                    layoutParams = parms
                }

                val textView = TextView(context).apply {
                    val params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    setTextColor(Color.WHITE)
                    setBackgroundResource(R.color.background)
                    gravity = Gravity.CENTER
                    textSize = 30f
                    text = (photos.size - 5).toString() + "+"
                }
                templl.addView(imageView3)
                templl.addView(imageView4)
                frameLayout.addView(imageView5)
                frameLayout.addView(textView)
                templl.addView(frameLayout)
                all.addView(tempLinearLayout)
                all.addView(templl)
            }

        }
        return all
    }

    private fun refreshAdapter(stories: ArrayList<Story>, recyclerView: RecyclerView) {
        var adapter = StoryAdapter(context, stories)
        recyclerView.adapter = adapter
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class HeadViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv_mind = view.findViewById<TextView>(R.id.tv_mind)
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile = view.findViewById<ShapeableImageView>(R.id.iv_profile)
        var tv_fullname = view.findViewById<TextView>(R.id.tv_fullname)
        var ll_photo_list1 = view.findViewById<LinearLayout>(R.id.ll_photo_list1)


    }

    class PostLinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile = view.findViewById<ShapeableImageView>(R.id.iv_profile)
        var tv_fullname = view.findViewById<TextView>(R.id.tv_fullname)
        var tv_title = view.findViewById<TextView>(R.id.tv_title)
        var tv_website = view.findViewById<TextView>(R.id.tv_website)
        var iv_post = view.findViewById<ImageView>(R.id.iv_post)
        var tv_post = view.findViewById<TextView>(R.id.tv_post)


    }

    class StoryViewHolder(var context: Context, val view: View) : RecyclerView.ViewHolder(view) {
        var recyclerView: RecyclerView
        var adapter: StoryAdapter?=null

        init {
            recyclerView = view.findViewById(R.id.recycler_view)
            val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = manager
        }
    }
}