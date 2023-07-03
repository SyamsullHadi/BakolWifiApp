package com.example.bakolwifiapp.application

import android.app.Application
import com.example.bakolwifiapp.repository.WifiRepository

class WifiApp: Application() {
    val database by lazy { WifiDatabase.getDatabase(this) }
    val repository by lazy { WifiRepository(database.wifiDao()) }
}