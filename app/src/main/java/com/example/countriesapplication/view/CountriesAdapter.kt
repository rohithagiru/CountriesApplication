package com.example.countriesapplication.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapplication.data.model.CountriesInfo
import com.example.countriesapplication.R

/**
 * Adapter for RecyclerView to display the data
 */
class CountriesAdapter(
    private val context: Context,
    private val countriesList: CountriesInfo?
    ) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.countries_list, parent, false)
        return CountriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countriesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.countryNameTextView.text = countriesList?.get(position)?.name
        holder.regionTextView.text = countriesList?.get(position)?.region
        holder.countryCodeTextView.text = countriesList?.get(position)?.code
        holder.capitalTextView.text = countriesList?.get(position)?.capital
    }

    class CountriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var countryNameTextView : TextView
        var regionTextView : TextView
        var countryCodeTextView : TextView
        var capitalTextView : TextView

        init {
            countryNameTextView = view.findViewById(R.id.country_name)
            regionTextView = view.findViewById(R.id.region_name)
            countryCodeTextView = view.findViewById(R.id.country_code)
            capitalTextView = view.findViewById(R.id.country_capital)
        }
    }
}
