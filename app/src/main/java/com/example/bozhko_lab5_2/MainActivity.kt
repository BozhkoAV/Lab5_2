package com.example.bozhko_lab5_2

import android.app.Application
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bozhko_lab5_2.databinding.ActivityMainBinding
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val executorService by lazy {(application as ExecutorServiceApplication).executor}
    private lateinit var executor: Future<*>

    private fun setImage() {
        executor = executorService.submit {
            Log.i("ExecutorService", "${Thread.currentThread()}: Downloading image")
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
        executor.cancel(true)
    }
}

class ExecutorServiceApplication: Application() {
    var executor: ExecutorService = Executors.newSingleThreadExecutor()
}