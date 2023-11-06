package com.main.guestwise.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "language")
data class Language(
    @PrimaryKey val id: Int = 0,

    @ColumnInfo(name = "languageCode") val code: String
)
