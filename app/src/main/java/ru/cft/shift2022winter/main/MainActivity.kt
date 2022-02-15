package ru.cft.shift2022winter.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import ru.cft.shift2022winter.R
import ru.cft.shift2022winter.databinding.ActivityMainBinding
import ru.cft.shift2022winter.domain.model.Valute
import ru.cft.shift2022winter.domain.model.database.ValuteModel
import ru.cft.shift2022winter.info.InfoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ListViewModel by viewModels {
        ValuteViewModelFactory(application)
    }

    private val adapter = ValutesAdapter {
		viewModel.openDetailsScreen(it)
	}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.openDetailsScreenEvent.observe(this, ::openDetailsScreen)
        viewModel.loadError.observe(this, ::showError)
        viewModel.valuteList.observe(this, ::bindCharacter)
        binding.recyclerView.adapter = adapter

        viewModel.startUpdates()

        binding.button.setOnClickListener {
            viewModel.loadValutes()
        }
    }

    private fun bindCharacter(list: List<ValuteModel>) {
        adapter.valutes = list
    }

    private fun openDetailsScreen(valute: ValuteModel) {
        InfoActivity.start(this, valute)
    }

    private fun showError(message: String) {
        Toast.makeText(this, getString(R.string.error) + message, Toast.LENGTH_LONG).show()
    }
}