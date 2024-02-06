package com.example.countriesapplication.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapplication.R
import com.example.countriesapplication.data.repository.CountriesRepositoryImpl
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: CountriesViewModel
    private lateinit var countriesAdapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this
        val repository = CountriesRepositoryImpl()
        val provider = CountryViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, provider)[CountriesViewModel::class.java]
        fetchCountriesInfo(
            context = context, viewModel = viewModel
        )
    }

    private fun fetchCountriesInfo(
        context: Context, viewModel: CountriesViewModel
    ) {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val title = findViewById<TextView>(R.id.title)

        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { response ->
                    when (response) {
                        is CountriesState.Success -> {
                            progressBar.visibility = View.INVISIBLE
                            response.data?.let { response ->
                                title.setText(R.string.success_Message)
                                recyclerView.layoutManager = LinearLayoutManager(context)
                                countriesAdapter = CountriesAdapter(
                                    context, response.body()
                                )
                                recyclerView.adapter = countriesAdapter
                            }
                        }

                        is CountriesState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }

                        is CountriesState.Error -> {
                            progressBar.visibility = View.INVISIBLE
                            title.setText(R.string.error_message)
                        }
                    }
                }
        }
    }
}