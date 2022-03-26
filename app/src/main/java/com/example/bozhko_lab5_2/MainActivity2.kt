package com.example.bozhko_lab5_2

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bozhko_lab5_2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val scope = CoroutineScope(Dispatchers.IO)

    private fun setImage() {
        scope.launch(Dispatchers.IO) {
            Log.i("Coroutine", "${Thread.currentThread()}: Downloading image")
            val newurl = URL("https://searchthisweb.com/wallpaper/landscape_4100x2733_8dyho.jpg")
            val mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
            binding.imageView.post {
                binding.imageView.setImageBitmap(mIcon_val)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnDownload.setOnClickListener {
            setImage()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}