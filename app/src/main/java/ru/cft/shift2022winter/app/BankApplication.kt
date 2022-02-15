package ru.cft.shift2022winter.app

import android.app.Application
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.cft.shift2022winter.data.ValuteDatasource
import ru.cft.shift2022winter.data.ValuteDatasourceImpl
import ru.cft.shift2022winter.domain.repository.ValuteRepository
import ru.cft.shift2022winter.data.ValuteRepositoryImpl
import ru.cft.shift2022winter.data.database.ValuteDatabase

class BankApplication: Application() {
    private companion object {
        var BASE_URL = "https://www.cbr-xml-daily.ru/"
    }

    lateinit var valuteRepository: ValuteRepositoryImpl

    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }

    private fun configureRetrofit() {
        val okHttpClient =  buildHttpClient()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()


        val valuteDao = ValuteDatabase.getDatabase(this).valuteDao()
        valuteRepository = ValuteRepositoryImpl(
            ValuteDatasourceImpl(retrofit.create()), valuteDao
        )
    }

    private fun buildHttpClient(): OkHttpClient {
        val interceptor = buildInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun buildInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}