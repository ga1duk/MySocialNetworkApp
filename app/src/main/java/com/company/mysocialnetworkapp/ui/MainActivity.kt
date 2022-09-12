package com.company.mysocialnetworkapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.company.mysocialnetworkapp.PostApi
import com.company.mysocialnetworkapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val response = PostApi.retrofitService.getPosts()
            if (!response.isSuccessful) {
                throw Error("kdfkjlds")
            } else {
                val body = response.body()?.get(0)?.author
                binding.tvAuthor.text = body
            }
        }
    }
}