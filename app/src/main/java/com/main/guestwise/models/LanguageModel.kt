package com.main.guestwise.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Language")
data class LanguageModel(
    @PrimaryKey(autoGenerate = true) val _roomId: Long? = null,
    val id: Int? = null,
    val lang: String? = null
)