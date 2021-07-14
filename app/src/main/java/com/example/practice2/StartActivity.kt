package com.example.practice2

import android.content.Intent
import android.graphics.Color.alpha
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.practice2.databinding.ActivityStartBinding


class StartActivity : AppCompatActivity() {

    lateinit var binding:ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val animation = AnimationUtils.loadAnimation(this, R.anim.alpha)

        binding.imageStart.startAnimation(animation)

        var handler = Handler()
        handler.postDelayed( {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }, 3000)
    }

    override fun onPause(){
        super.onPause()
        finish()
    }
}