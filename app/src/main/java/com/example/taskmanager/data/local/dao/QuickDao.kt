package com.example.taskmanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanager.data.local.entities.QuickDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class QuickDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAllQuickNotes(projects: List<QuickDbEntity>)

    @Query("SELECT * FROM projects WHERE owner_id = :ownerId ")
    abstract fun getQuicks(ownerId: String): Flow<List<QuickDbEntity?>>
}
