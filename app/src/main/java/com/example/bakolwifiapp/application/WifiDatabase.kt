package com.example.bakolwifiapp.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bakolwifiapp.dao.WifiDao
import com.example.bakolwifiapp.model.Wifi

@Database(entities = [Wifi::class], version = 1, exportSchema = false)
abstract class WifiDatabase: RoomDatabase() {
    abstract fun wifiDao(): WifiDao

    companion object{
        private var INSTANCE: WifiDatabase? = null

        fun getDatabase(context: Context): WifiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WifiDatabase::class.java,
                    "wifi_dtatabase_1"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }

        }
    }
}