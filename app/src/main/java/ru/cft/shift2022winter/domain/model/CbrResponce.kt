package ru.cft.shift2022winter.domain

import com.google.gson.annotations.SerializedName
import ru.cft.shift2022winter.domain.model.Valutes

data class CbrResponse(
    @SerializedName("Date")
    val date : String,
    @SerializedName("PreviousDate")
    val previousDate : String,
    @SerializedName("PreviousURL")
    val previousURL : String,
    @SerializedName("Timestamp")
    val timestamp : String,
    @SerializedName("Valute")
    val valutes : Valutes
)
