package com.example.mapfortravelers

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import com.yandex.maps.mobile.BuildConfig

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Reading API key from BuildConfig.
        // Do not forget to add your MAPKIT_API_KEY property to local.properties file.
        MapKitFactory.setApiKey("6ef8d0c5-2284-480e-aac8-1282e21b2c6b")
    }
}