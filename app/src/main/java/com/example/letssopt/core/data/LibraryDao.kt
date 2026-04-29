package com.example.letssopt.core.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LibraryEntity)

    @Query("SELECT * FROM library_table WHERE id = :id")
    suspend fun getItemById(id: Long): LibraryEntity?

    @Query("SELECT * FROM library_table")
    fun getAllItems(): Flow<List<LibraryEntity>>

    @Delete
    suspend fun delete(libraryEntity: LibraryEntity)
}