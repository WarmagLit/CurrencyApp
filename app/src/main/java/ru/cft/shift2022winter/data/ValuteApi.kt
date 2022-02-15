package ru.cft.shift2022winter.data

import retrofit2.Response
import retrofit2.http.GET
import ru.cft.shift2022winter.domain.CbrResponse

interface ValuteApi {
    @GET("./daily_json.js")
    suspend fun getAll(): Response<CbrResponse>
}