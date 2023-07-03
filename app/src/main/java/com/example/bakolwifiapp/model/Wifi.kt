package com.example.bakolwifiapp.model

import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Wifi_table")
data class Wifi (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val tlp: String,
    val address: String
) : Parcelable