package com.company.mysocialnetworkapp.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
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

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onSend(post: Post) {}
}

class PostsAdapter(private val listener: OnInteractionListener) : ListAdapter<Post, PostsViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding, listener)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostsViewHolder(private val binding: CardPostBinding, private val listener: OnInteractionListener) :
    RecyclerView.ViewHolder(binding.root) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(post: Post) {
        with(binding) {
            btnLike.isChecked = post.likedByMe

            if (post.authorAvatar == null) {
                ImageLoader.loadRoundedImage(
                    view = binding.ivPostAvatar,
                    resId = R.drawable.default_avatar
                )
            } else {
                ImageLoader.loadRoundedImage(view = binding.ivPostAvatar, path = post.authorAvatar)
            }

            tvAuthor.text = post.author

            val postPublishDate = post.published
            val odt = OffsetDateTime.parse(postPublishDate)
            val postPublishDateFormatted =
                odt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
            tvPublished.text = postPublishDateFormatted

            if (post.link != null) {
                tvContent.text = root.resources.getString(
                    R.string.post_content_and_link_text,
                    post.content,
                    post.link
                )
            } else {
                tvContent.text = post.content
            }

            if (post.attachment != null) {
                if (post.attachment.type == AttachmentType.IMAGE) {
                    ivAttachment.visibility = View.VISIBLE
                    ImageLoader.loadImage(view = ivAttachment, path = post.attachment.url)
                }
            } else {
                ivAttachment.visibility = View.GONE
            }

            btnLike.setOnClickListener {
                listener.onLike(post)
            }

            btnSend.setOnClickListener {
                listener.onSend(post)
                btnSend.isChecked = false
            }
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