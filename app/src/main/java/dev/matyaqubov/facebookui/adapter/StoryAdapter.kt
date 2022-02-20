package dev.matyaqubov.facebookui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import dev.matyaqubov.facebookui.R
import dev.matyaqubov.facebookui.model.Story

class StoryAdapter(var context: Context , var items:ArrayList<Story>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_story_view,parent,false)
        return StoryViewHolder(view)
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story=items[position]

        if (holder is StoryViewHolder){
            holder.iv_background.setImageResource(story.profile)
            holder.iv_profile.setImageResource(story.profile)
            holder.tv_fullname.text=story.fullname
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_background = view.findViewById<ShapeableImageView>(R.id.iv_background)
        var iv_profile=view.findViewById<ShapeableImageView>(R.id.iv_profile)
        var tv_fullname=view.findViewById<TextView>(R.id.tv_fullname)
    }
}