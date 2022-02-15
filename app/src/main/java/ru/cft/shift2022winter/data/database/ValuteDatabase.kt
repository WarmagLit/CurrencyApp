package ru.cft.shift2022winter.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.cft.shift2022winter.domain.model.database.ValuteModel

@Database(entities = [ValuteModel::class], version = 2, exportSchema = false)

abstract class ValuteDatabase : RoomDatabase() {

    abstract fun valuteDao(): ValuteDao

    companion object {
        @Volatile
        private var INSTANCE: ValuteDatabase? = null

        fun getDatabase(context: Context): ValuteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ValuteDatabase::class.java,
                    "valuteDatabase"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

    }
}