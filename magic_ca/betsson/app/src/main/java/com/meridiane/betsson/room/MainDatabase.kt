package com.meridiane.betsson.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.meridiane.betsson.model.MatchesType

// build table bd
@Database(entities = [MatchesType::class], version = 1)
abstract class MainDatabase: RoomDatabase() {
    abstract fun getDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: MainDatabase ?= null

        fun getDateBase(context: Context): MainDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,MainDatabase::class.java,
                    "matches_type.db").allowMainThreadQueries().build()
                instance
            }
        }
    }
}