package com.isabellacp.dev.app.yomu.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.isabellacp.dev.app.yomu.activities.DetailActivity
import com.isabellacp.dev.app.yomu.databinding.ListItemBinding
import com.isabellacp.dev.app.yomu.models.Data


class SearchAdapter(var list: ArrayList<Data>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.title.text = item.attributes.canonicalTitle ?: "title"
            val imageLink = item.attributes.posterImage?.medium
            binding.poster.load(imageLink) {
                crossfade(true)
                crossfade(800)
            }
            binding.score.text = item.attributes.averageRating
            val status = item.attributes.status
            binding.mediaStatus.text = "($status)"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            ViewCompat.setTransitionName(holder.binding.poster, "poster_image")
            intent.putExtra("currentItem", list[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size


}