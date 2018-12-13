package com.topeomot.youtubekeywordapp.adapter

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.topeomot.youtubekeywordapp.R
import com.topeomot.youtubekeywordapp.model.Snippet
import com.topeomot.youtubekeywordapp.ui.player.PlayerActivity
import com.topeomot.youtubekeywordapp.util.GlideApp
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import kotlinx.android.synthetic.main.video_box.view.*
import org.jetbrains.anko.intentFor

class SearchListAdapter(
    val activity: Activity,
    val searchResults: ArrayList<Snippet>,
    val width: Int) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mContext = parent!!.context
        val inflater = LayoutInflater.from(mContext)
        val customView = inflater.inflate(R.layout.video_box, parent, false)
        val viewHolder = ViewHolder(customView)
        return viewHolder
    }

    override fun getItemCount(): Int = searchResults.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val snippet = searchResults[position].snippet
        holder.title!!.text = snippet.title
        holder.subTitle!!.text = "${snippet.channelTitle} ${snippet.publishedAt}"


        holder.pix?.layoutParams?.width = width

        if(snippet.thumbnails.high.url.isNotBlank()) {
            holder.pix?.layoutParams?.height = width.times(snippet.thumbnails.high.height
                .div(snippet.thumbnails.high.width.toDouble())).toInt()
            GlideApp.with(activity).load(snippet.thumbnails.high.url).into(holder.pix!!)

        } else if(snippet.thumbnails.medium.url.isNotBlank()) {
            holder.pix?.layoutParams?.height = width.times(snippet.thumbnails.medium.height
                .div(snippet.thumbnails.medium.width.toDouble())).toInt()
            GlideApp.with(activity).load(snippet.thumbnails.medium.url).into(holder.pix!!)

        } else {
            holder.pix?.layoutParams?.height = width.times(snippet.thumbnails.default.height
                .div(snippet.thumbnails.default.width.toDouble())).toInt()
            GlideApp.with(activity).load(snippet.thumbnails.default.url).into(holder.pix!!)
        }

        val videoId = searchResults[position].id.videoId


        holder.pix?.setOnClickListener { v: View? ->

            activity.startActivity(activity.intentFor<PlayerActivity>("videoId" to videoId))
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pix: ImageView? = null
        var title: TextView? = null
        var subTitle: TextView? = null
        var container: ConstraintLayout? = null
        init {
            pix = view.pix
            title = view.title
            subTitle = view.subtitle
            container = view.container
        }
    }
}