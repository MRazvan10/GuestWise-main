package com.main.guestwise.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "language")
data class Language(
    @PrimaryKey val id: Int = 0,

    @ColumnInfo(name = "languageCode") val code: String
)
