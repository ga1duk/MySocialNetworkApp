package com.company.mysocialnetworkapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.company.mysocialnetworkapp.R
import com.company.mysocialnetworkapp.adapter.OnInteractionListener
import com.company.mysocialnetworkapp.adapter.PostsAdapter
import com.company.mysocialnetworkapp.api.PostsApiService
import com.company.mysocialnetworkapp.databinding.FragmentPostsBinding
import com.company.mysocialnetworkapp.dto.Post
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PostsFragment : Fragment() {

    @Inject
    lateinit var apiService: PostsApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostsBinding.inflate(inflater, container, false)

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onSend(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    if (post.link == null) {
                        putExtra(Intent.EXTRA_TEXT, post.content)
                    } else {
                        putExtra(Intent.EXTRA_TEXT, "${post.content} ${post.link}")
                    }
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_send_post))
                startActivity(shareIntent)
            }
        })
        binding.rvPosts.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            val response = apiService.getPosts()
            if (!response.isSuccessful) {
                throw Error("kdfkjlds")
            } else {
                val body = response.body()
                adapter.submitList(body)
            }
        }

        return binding.root
    }
}