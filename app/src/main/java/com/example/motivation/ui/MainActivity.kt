package com.example.motivation.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infra.SecutiryPreferences
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.PHRASEFILTER.ALL

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonNewPhrase.setOnClickListener (this)
        binding.imageAll.setOnClickListener (this)
        binding.imageHappy.setOnClickListener (this)
        binding.imageLight.setOnClickListener (this)

        handleUsername()
        handleFilter(R.id.image_all)
        handleNextPhrase(categoryId)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_new_phrase){
            handleNextPhrase(categoryId)
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_light)) {
            handleFilter(view.id)
        }
    }

    private fun handleUsername() {
        val name = SecutiryPreferences(this).getString(MotivationConstants.KEY.USE_NAME)
        binding.textTitle.text = "Olá, $name!"
    }

    private fun handleFilter(id: Int){

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageLight.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when(id){
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.ALL
                handleNextPhrase(categoryId)
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.HAPPY
                handleNextPhrase(categoryId)

            }
            R.id.image_light -> {
                binding.imageLight.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.SUNNY
                handleNextPhrase(categoryId)
            }
        }
    }

    private fun handleNextPhrase(categoryId: Int) {
        binding.textMotivation.text = Mock().getPhrase(categoryId)
    }
}