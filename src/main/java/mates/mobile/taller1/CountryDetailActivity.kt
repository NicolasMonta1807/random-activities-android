package mates.mobile.taller1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import mates.mobile.taller1.databinding.ActivityCountryDetailBinding
import mates.mobile.taller1.model.Country
import java.util.concurrent.Executors

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryDetailBinding
    private lateinit var currentCountry: Country

    private fun loadFlag() {
        val executor = Executors.newSingleThreadExecutor()
        val handler = android.os.Handler(Looper.getMainLooper())
        var image: Bitmap? = null
        val url = "https://flagcdn.com/256x192/${currentCountry.abbreviation.lowercase()}.png"
        executor.execute {
            try {
                val `in` = java.net.URL(url).openStream()
                image = BitmapFactory.decodeStream(`in`)

                handler.post {
                    binding.countryFlag.setImageBitmap(image)
                    binding.countryFlag.alpha = 1.0F
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentCountry = intent.getSerializableExtra("country") as Country
        loadFlag()

        binding.countryName.text = String.format(
            resources.getString(R.string.countryDetail),
            currentCountry.name,
            currentCountry.abbreviation
        )

        binding.countryCapital.text = String.format(resources.getString(R.string.countryCapital), currentCountry.capital)
    }
}