package com.maksimzotov.news.data.android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maksimzotov.news.data.android.presentation.AndroidSettingsTableNames
import com.maksimzotov.news.presentation.entities.DarkTheme
import kotlinx.coroutines.flow.Flow

@Dao
interface AndroidSettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setDarkTheme(darkTheme: DarkTheme)

    @Query("SELECT * FROM ${AndroidSettingsTableNames.DARK_THEME}")
    fun getDarkTheme(): Flow<DarkTheme?>
}