package mates.mobile.taller1.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import mates.mobile.taller1.R
import mates.mobile.taller1.model.Country
import java.util.concurrent.Executors

class CountryAdapter(context: Context, resource: Int, countries: MutableList<Country>) :
    ArrayAdapter<Country>(context, resource, countries) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var currentView = convertView
        if (currentView == null) {
            currentView = LayoutInflater.from(context).inflate(R.layout.country_info, parent, false)
        }

        // Get the position of the view from Adapter
        val currentCountry = getItem(position)

        val flag = currentView!!.findViewById<ImageView>(R.id.countryFlag)

        // Load image to imageview
        val executor = Executors.newSingleThreadExecutor()
        val handler = android.os.Handler(Looper.getMainLooper())
        var image: Bitmap? = null
        val url = "https://flagsapi.com/${currentCountry?.abbreviation}/flat/64.png"
        executor.execute {
            try {
                val `in` = java.net.URL(url).openStream()
                image = BitmapFactory.decodeStream(`in`)

                handler.post {
                    flag?.setImageBitmap(image)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val countryName = currentView.findViewById<TextView>(R.id.countryName)
        val countryCapital = currentView.findViewById<TextView>(R.id.countryCapital)

        Log.i("MainApp", "Capital: ${currentCountry?.capital}")

        if (currentCountry != null) {
            countryName.text = String.format(
                context.resources.getString(R.string.countryName),
                currentCountry.id,
                currentCountry.name,
                currentCountry.abbreviation
            )

            countryCapital.text = String.format(
                context.resources.getString(R.string.countryCapital),
                currentCountry.capital
            )
        }

        return currentView
    }
}