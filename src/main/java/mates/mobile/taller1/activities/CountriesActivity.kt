package mates.mobile.taller1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mates.mobile.taller1.adapters.CountryAdapter
import mates.mobile.taller1.databinding.ActivityCountriesBinding
import mates.mobile.taller1.model.Country
import org.json.JSONObject

class CountriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountriesBinding
    private lateinit var countries: MutableList<Country>

    private fun loadCountries() {
        countries = mutableListOf<Country>()

        val data = baseContext.assets.open("countries.json").bufferedReader().use { it.readText() }
        var jsonHandler = JSONObject(data)
        var countriesArray = jsonHandler.getJSONArray("countries")

        for (i in 0..<countriesArray.length()) {
            val jsonObject = countriesArray.getJSONObject(i)
            val capital = jsonObject.getString("capital")
            val intName = jsonObject.getString("int_name")
            val abb = jsonObject.getString("abbreviation")
            countries.add(Country(i + 1, intName, capital, abb))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadCountries()
        val adapter = CountryAdapter(baseContext, 0, countries)
        binding.countriesList.adapter = adapter

        binding.countriesList.setOnItemClickListener { _, _, position, _ ->
            val country = adapter.getItem(position)
            val intent = Intent(baseContext, CountryDetailActivity::class.java)
            intent.putExtra("country", country)
            startActivity(intent)
        }
    }
}