package com.example.taskmanager.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanager.room.entities.ProjectDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addProject(project: ProjectDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAllProjects(projects: List<ProjectDbEntity>)

    @Query("SELECT * FROM projects WHERE owner_id = :ownerId ")
    abstract fun getProjects(ownerId: String): Flow<List<ProjectDbEntity?>>

}