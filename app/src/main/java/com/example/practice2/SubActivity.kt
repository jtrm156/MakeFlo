package com.example.practice2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice2.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubBinding

    companion object {
        const val Music = "app_preferences"
    }

    private lateinit var mPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var touchFlag = false
        mPreferences = getSharedPreferences(SubActivity.Music, MODE_PRIVATE);
        val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()

        var intent = Intent(this, MainActivity::class.java)

        if (mPreferences.getInt("music_img", 0) == 0) {
            finish()
        } else {
            binding.img3Sub.setImageResource(mPreferences.getInt("music_img", 0))
            binding.img2Sub.setImageResource(mPreferences.getInt("music_img", 0))
            binding.txt2Sub.setText(mPreferences.getString("music_name", null))
            binding.txt3Sub.setText(mPreferences.getString("music_contents", null))
            binding.progressBar2.setProgress(mPreferences.getInt("music_length", 0))
        }

        binding.img3Sub.setOnClickListener()
        {
            finish()
        }
        binding.img1Sub.setOnClickListener()
        {
            finish()
        }

        binding.img4Sub.setOnClickListener()
        {
            if(touchFlag == false)
            {
                binding.img4Sub.setImageResource(R.drawable.heart2)
                touchFlag = true
            }
            else
            {
                binding.img4Sub.setImageResource(R.drawable.heart)
                touchFlag = false
            }
        }
    }
}


