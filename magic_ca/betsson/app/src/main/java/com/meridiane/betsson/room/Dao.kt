package com.meridiane.betsson.room

import androidx.room.*
import androidx.room.Dao
import com.meridiane.betsson.model.MatchesType
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<MatchesType>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:MatchesType?)

    @Query("DELETE FROM matches_type WHERE id IS :id")
    suspend fun deleteEntity(id:Int)

    @Query("SELECT * FROM matches_type")
    fun getAll(): Flow<List<MatchesType>>

    @Update
    fun updateItem(item: MatchesType)
}