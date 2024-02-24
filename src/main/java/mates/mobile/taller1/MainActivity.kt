package mates.mobile.taller1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mates.mobile.taller1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ticTacToe.setOnClickListener {
            startActivity(Intent(baseContext, TicTacToeActivity::class.java))
        }

        binding.countries.setOnClickListener {
            startActivity(Intent(baseContext, CountriesActivity::class.java))
        }

        binding.randomGreet.setOnClickListener {
            val intent = Intent(baseContext, RandomGreetActivity::class.java)
            val lang = binding.langSelector.selectedItem.toString()
            intent.putExtra("langSelected", lang)
            startActivity(intent)
        }
    }
}