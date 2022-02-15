package ru.cft.shift2022winter.domain.model

import com.google.gson.annotations.SerializedName

open class Valute(
    open val id : String,
    open val numCode : Int,
    open val charCode : String,
    open val nominal : Int,
    open val name : String,
    open val value : Double,
    open val previous : Double
)
