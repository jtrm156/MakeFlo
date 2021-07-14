package com.example.practice2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice2.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var intent = Intent(this,MainActivity::class.java)
        val img1 = intent.getStringExtra("data")

        if (img1 != null) {
            binding.img3Sub.setImageResource(img1.toInt())
            binding.img2Sub.setImageResource(img1.toInt())
        }

        else{
            finish()
        }

        binding.img3Sub.setOnClickListener()
        {
            startActivity(intent)
        }
    }
}