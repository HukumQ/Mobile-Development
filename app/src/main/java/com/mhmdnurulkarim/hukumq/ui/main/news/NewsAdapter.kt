package com.mhmdnurulkarim.hukumq.ui.main.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mhmdnurulkarim.hukumq.R
import com.mhmdnurulkarim.hukumq.data.model.News
import com.mhmdnurulkarim.hukumq.databinding.ItemNewsBinding
import com.mhmdnurulkarim.hukumq.ui.main.news.NewsAdapter.MyViewHolder

class NewsAdapter (private val onItemClick: (News) -> Unit) : ListAdapter<News, MyViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    class MyViewHolder(
        private val binding: ItemNewsBinding,
        val onItemClick: (News) -> Unit
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(news: News) {
            binding.tvItemTitle.text = news.title
            binding.tvItemPublishedDate.text = news.pubDate
            Glide.with(itemView.context)
                .load(news.thumbnail)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(binding.imgPoster)
            itemView.setOnClickListener {
                onItemClick(news)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<News> =
            object : DiffUtil.ItemCallback<News>() {
                override fun areItemsTheSame(oldUser: News, newUser: News): Boolean {
                    return oldUser.title == newUser.title
                }

                override fun areContentsTheSame(oldUser: News, newUser: News): Boolean {
                    return oldUser == newUser
                }
            }
    }
}