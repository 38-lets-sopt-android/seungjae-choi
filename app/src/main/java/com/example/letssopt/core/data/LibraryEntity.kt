package com.example.letssopt.core.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "library_table")
data class LibraryEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "image_res")
    val imageRes: Int,
    @ColumnInfo(name = "title")
    val title: String
)