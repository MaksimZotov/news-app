package com.maksimzotov.news.presentation.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maksimzotov.news.data.android.presentation.AndroidSettingsTableNames

@Entity(tableName = AndroidSettingsTableNames.DARK_THEME)
data class DarkTheme(val isAble: Boolean) {
    @PrimaryKey var id = 0
}