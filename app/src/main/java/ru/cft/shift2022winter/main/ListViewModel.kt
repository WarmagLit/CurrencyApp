package ru.cft.shift2022winter.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.isActive
import ru.cft.androidlivecoding.utils.SingleLiveEvent
import ru.cft.shift2022winter.data.ValuteRepositoryImpl
import ru.cft.shift2022winter.domain.model.Valute
import ru.cft.shift2022winter.domain.model.Valutes
import ru.cft.shift2022winter.domain.model.database.ValuteModel

class ListViewModel(
    private val repository: ValuteRepositoryImpl
) : ViewModel() {

    var valuteList: LiveData<List<ValuteModel>> = repository.valutes
    val openDetailsScreenEvent = SingleLiveEvent<ValuteModel>()
    val loadError = SingleLiveEvent<String>()

    val scope = viewModelScope // could also use an other scope such as viewModelScope if available
    var job: Job? = null

    private val exceptionHandler =
        CoroutineExceptionHandler { _, throwable -> loadError.postValue(throwable.message) }

    fun loadValutes() {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.getAll()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val valutes = convertResponseToValuteList(response.body()!!.valutes)
                        for (valute in valutes) {
                            repository.addValute(
                                ValuteModel(
                                    valute.id,
                                    valute.numCode,
                                    valute.charCode,
                                    valute.nominal,
                                    valute.name,
                                    valute.value,
                                    valute.previous
                                )
                            )
                        }

                    }
                } else {
                    loadError.value = response.message()
                }
            }
        }
    }


    fun startUpdates() {
        stopUpdates()
        job = scope.launch {
            while(true) {
                loadValutes()
                delay(10000)
            }
        }
    }

    fun stopUpdates() {
        job?.cancel()
        job = null
    }

    fun convertResponseToValuteList(valutes: Valutes): List<Valute> {
        return mutableListOf(
            valutes.AMD,
            valutes.AUD,
            valutes.AZN,
            valutes.BGN,
            valutes.BRL,
            valutes.BYN,
            valutes.CAD,
            valutes.CHF,
            valutes.CNY,
            valutes.CZK,
            valutes.DKK,
            valutes.EUR,
            valutes.GBP,
            valutes.HKD,
            valutes.HUF,
            valutes.INR,
            valutes.JPY,
            valutes.KGS,
            valutes.KRW,
            valutes.KZT,
            valutes.MDL,
            valutes.NOK,
            valutes.PLN,
            valutes.RON,
            valutes.SEK,
            valutes.SGD,
            valutes.TJS,
            valutes.TMT,
            valutes.TRY,
            valutes.UAH,
            valutes.USD,
            valutes.UZS,
            valutes.XDR,
            valutes.ZAR
        )
    }

    fun openDetailsScreen(valute: ValuteModel) {
        openDetailsScreenEvent.value = valute
    }


}