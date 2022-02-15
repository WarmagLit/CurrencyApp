package ru.cft.shift2022winter.domain.repository

import retrofit2.Response
import ru.cft.shift2022winter.domain.CbrResponse
import ru.cft.shift2022winter.domain.model.database.ValuteModel

interface ValuteRepository{
    suspend fun addValute(valuteModel: ValuteModel)

    suspend fun getAll(): Response<CbrResponse>

    suspend fun getValutes(): List<ru.cft.shift2022winter.domain.model.Valute>?
}