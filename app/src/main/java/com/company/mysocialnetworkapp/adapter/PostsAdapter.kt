package com.company.mysocialnetworkapp.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.mysocialnetworkapp.R
import com.company.mysocialnetworkapp.databinding.CardPostBinding
import com.company.mysocialnetworkapp.dto.AttachmentType
import com.company.mysocialnetworkapp.dto.Post
import com.company.mysocialnetworkapp.util.ImageLoader
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class PostsAdapter : ListAdapter<Post, PostsViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostsViewHolder(private val binding: CardPostBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(post: Post) {
        if (post.authorAvatar == null) {
            ImageLoader.loadRoundedImage(
                view = binding.ivPostAvatar,
                resId = R.drawable.default_avatar
            )
        } else {
            ImageLoader.loadRoundedImage(view = binding.ivPostAvatar, path = post.authorAvatar)
        }

        binding.tvAuthor.text = post.author

        val postPublishDate = post.published
        val odt = OffsetDateTime.parse(postPublishDate)
        val postPublishDateFormatted = odt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        binding.tvPublished.text = postPublishDateFormatted

        binding.tvContent.text = post.content

        if (post.attachment != null) {
            if (post.attachment.type == AttachmentType.IMAGE) {
                ImageLoader.loadImage(view = binding.ivAttachment, path = post.attachment.url)
            }
        } else {
            ImageLoader.loadImage(view = binding.ivAttachment, path = "")
        }
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