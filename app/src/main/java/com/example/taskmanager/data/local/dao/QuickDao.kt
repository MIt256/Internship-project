package com.example.taskmanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanager.data.local.entities.ProjectDbEntity
import com.example.taskmanager.data.local.entities.QuickDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class QuickDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAllQuickNotes(quickNotes: List<QuickDbEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addQuickNote(note: QuickDbEntity)

    @Query("SELECT * FROM quicks WHERE owner_id = :ownerId ")
    abstract fun getQuickNotes(ownerId: String): Flow<List<QuickDbEntity?>>
}
