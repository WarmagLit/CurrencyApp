package ru.cft.shift2022winter.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.cft.shift2022winter.domain.model.database.ValuteModel

@Dao
interface ValuteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addValute(valuteModel: ValuteModel)

    @Query("SELECT * FROM valute")
    fun getAllValutes(): LiveData<List<ValuteModel>>
}