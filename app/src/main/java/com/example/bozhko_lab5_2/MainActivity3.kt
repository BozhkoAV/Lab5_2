package com.example.bozhko_lab5_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bozhko_lab5_2.databinding.ActivityMainBinding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private fun setImage() {
        Glide.with(this).load("https://searchthisweb.com/wallpaper/landscape_4100x2733_8dyho.jpg")
            .into(binding.imageView);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnDownload.setOnClickListener {
            setImage()
        }
    }
}