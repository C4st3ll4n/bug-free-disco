package br.com.douglasmotta.hiltdependencyinjection

import android.app.Application
import com.androidnetworking.AndroidNetworking
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidNetworking.initialize(applicationContext)
    }
}