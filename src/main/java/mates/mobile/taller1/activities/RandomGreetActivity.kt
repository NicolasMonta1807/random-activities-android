package mates.mobile.taller1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mates.mobile.taller1.R
import mates.mobile.taller1.databinding.ActivityRandomGreetBinding
import kotlin.random.Random

class RandomGreetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRandomGreetBinding
    private var language: String? = null

    private fun loadImage() {
        when (language) {
            baseContext.resources.getString(R.string.en) -> {
                binding.bgImage.setImageResource(R.drawable.london)
                binding.greeting.text = baseContext.resources.getString(R.string.enGreet)
            }

            baseContext.resources.getString(R.string.es) -> {
                binding.bgImage.setImageResource(R.drawable.bogota)
                binding.greeting.text = baseContext.resources.getString(R.string.esGreet)
            }

            baseContext.resources.getString(R.string.fr) -> {
                binding.bgImage.setImageResource(R.drawable.paris)
                binding.greeting.text = baseContext.resources.getString(R.string.frGreet)
            }

            baseContext.resources.getString(R.string.jp) -> {
                binding.bgImage.setImageResource(R.drawable.tokyo)
                binding.greeting.text = baseContext.resources.getString(R.string.jpGreet)
            }
        }
    }

    private fun randomGreeting() {
        val tempLanguage = language
        while (tempLanguage == language)
        {
            when (Random.nextInt(0, 4)) {
                0 -> language = baseContext.resources.getString(R.string.en)
                1 -> language = baseContext.resources.getString(R.string.es)
                2 -> language = baseContext.resources.getString(R.string.fr)
                3 -> language = baseContext.resources.getString(R.string.jp)
            }
        }
        loadImage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomGreetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        language = intent.getStringExtra("langSelected")
        loadImage()

        binding.randomButton.setOnClickListener { randomGreeting() }
    }
}