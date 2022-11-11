package com.example.taskmanager.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.taskmanager.room.entities.ProjectDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectsDao {

    @Query("SELECT * FROM projects WHERE owner_id = :ownerId ")
    fun getProjects(ownerId: String): Flow<List<ProjectDbEntity?>>

}