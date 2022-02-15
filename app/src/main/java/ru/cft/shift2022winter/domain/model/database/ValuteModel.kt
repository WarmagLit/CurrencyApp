package ru.cft.shift2022winter.domain.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "valute")
data class ValuteModel(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val numCode : Int,
    val charCode : String,
    val nominal : Int,
    val name : String,
    val value : Double,
    val previous : Double
)
