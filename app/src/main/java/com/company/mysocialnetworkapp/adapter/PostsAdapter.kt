package com.company.mysocialnetworkapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.mysocialnetworkapp.R
import com.company.mysocialnetworkapp.databinding.CardPostBinding
import com.company.mysocialnetworkapp.dto.Post
import com.company.mysocialnetworkapp.util.ImageLoader

class PostsAdapter : ListAdapter<Post, PostsViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostsViewHolder(private val binding: CardPostBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        if (post.authorAvatar == null) {
            ImageLoader.load(view = binding.postAvatar, resId = R.drawable.default_avatar)
        } else {
            ImageLoader.load(view = binding.postAvatar, path = post.authorAvatar)
        }

        binding.tvAuthor.text = post.author
        binding.tvContent.text = post.content
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}