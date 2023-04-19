package com.meridiane.betsson.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches_type")
data class MatchesType(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "title")
    var titler: String,
    @ColumnInfo(name = "date")
    var dater: String,
    @ColumnInfo(name = "strIm1")
    var strIm1 : String,
    @ColumnInfo(name = "strIm2")
    var strIm2 : String,
    @ColumnInfo(name = "im1")
    var im1 : Int,
    @ColumnInfo(name = "im2")
    var im2 : Int,
    @ColumnInfo(name = "int1")
    var intew1 : String,
    @ColumnInfo(name = "int2")
    var intew2 : String,
    @ColumnInfo(name = "int3")
    var intew3 : String,
    @ColumnInfo(name = "boolean")
    var booleean: Boolean,
    @ColumnInfo(name = "type")
    var typede :String
)
