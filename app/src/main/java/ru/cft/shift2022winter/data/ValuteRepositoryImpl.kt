package ru.cft.shift2022winter.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Response
import ru.cft.shift2022winter.data.database.ValuteDao
import ru.cft.shift2022winter.domain.repository.ValuteRepository
import ru.cft.shift2022winter.domain.CbrResponse
import ru.cft.shift2022winter.domain.model.Valute
import ru.cft.shift2022winter.domain.model.database.ValuteModel

class ValuteRepositoryImpl(
    private val charactersDatasource: ValuteDatasource,
    private val valuteDao: ValuteDao
) :
    ValuteRepository {

    val valutes: LiveData<List<ValuteModel>> =
        valuteDao.getAllValutes()

    override suspend fun addValute(valuteModel: ValuteModel) {
        valuteDao.addValute(valuteModel)
    }

    override suspend fun getAll(): Response<CbrResponse> {
        return charactersDatasource.getAll()
    }

    override suspend fun getValutes(): List<Valute> {
        val valueList = mutableListOf<Valute>()
        if (valutes.value != null)
            for (valute in valutes.value!!) {
                valueList.add(
                    Valute(
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
        return valueList
    }


}