package com.company.mysocialnetworkapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.company.mysocialnetworkapp.R
import com.company.mysocialnetworkapp.adapter.OnInteractionListener
import com.company.mysocialnetworkapp.adapter.PostsAdapter
import com.company.mysocialnetworkapp.api.PostsApiService
import com.company.mysocialnetworkapp.databinding.FragmentPostsBinding
import com.company.mysocialnetworkapp.dto.Post
import com.company.mysocialnetworkapp.ui.dialog.SignInDialogFragment
import com.company.mysocialnetworkapp.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class PostsFragment : Fragment() {

    @Inject
    lateinit var apiService: PostsApiService

    private val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostsBinding.inflate(inflater, container, false)

        val signInDialogFragment = SignInDialogFragment()

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                if (viewModel.checkForUsersAuthentication()) {
                    if (!post.likedByMe) {
                        viewModel.likePostById(post.id)
                    } else {
                        viewModel.dislikePostById(post.id)
                    }
                }
            }

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

        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

        viewModel.isUserAuthorized.observe(viewLifecycleOwner) { state ->
            if (!state)
                signInDialogFragment.show(requireActivity().supportFragmentManager, "myDialog")
        }

        return binding.root
    }
}