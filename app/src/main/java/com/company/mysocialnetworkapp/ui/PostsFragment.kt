package com.company.mysocialnetworkapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.company.mysocialnetworkapp.api.PostsApiService
import com.company.mysocialnetworkapp.databinding.FragmentPostsBinding
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


        viewLifecycleOwner.lifecycleScope.launch {
            val response = apiService.getPosts()
            if (!response.isSuccessful) {
                throw Error("kdfkjlds")
            } else {
                val body = response.body()?.get(0)?.author
                binding.tvAuthor.text = body
            }
        }

        return binding.root
    }
}