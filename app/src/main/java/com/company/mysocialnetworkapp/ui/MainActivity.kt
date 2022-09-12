package com.company.mysocialnetworkapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.company.mysocialnetworkapp.api.PostsApiService
import com.company.mysocialnetworkapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: PostsApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val response = apiService.getPosts()
            if (!response.isSuccessful) {
                throw Error("kdfkjlds")
            } else {
                val body = response.body()?.get(0)?.author
                binding.tvAuthor.text = body
            }
        }
    }
}