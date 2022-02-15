package ru.cft.shift2022winter.data

import retrofit2.Response
import ru.cft.shift2022winter.domain.CbrResponse

interface ValuteDatasource {
    suspend fun getAll(): Response<CbrResponse>
}


class ValuteDatasourceImpl(private val valuteApi: ValuteApi) : ValuteDatasource {

    override suspend fun getAll(): Response<CbrResponse> =
        valuteApi.getAll()

}