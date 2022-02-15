package ru.cft.shift2022winter.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.cft.shift2022winter.app.BankApplication

class ValuteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel((application as BankApplication).valuteRepository) as T
    }
}