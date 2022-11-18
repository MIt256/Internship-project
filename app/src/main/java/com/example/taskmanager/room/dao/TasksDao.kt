package com.example.taskmanager.room.dao

import androidx.room.*
import com.example.taskmanager.room.entities.TaskDbEntity
import com.example.taskmanager.room.entities.TaskWithMembers
import com.example.taskmanager.room.entities.UserDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks WHERE owner_id = :ownerId ")
    fun getTasks(ownerId: String): Flow<List<TaskDbEntity?>>

    //todo check
    @Query("SELECT * FROM users, task_members WHERE users.id = task_members.user_id AND task_members.task_id = :taskId")
    fun getTaskMembers(taskId: String): Flow<List<UserDbEntity?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllTasks(tasks: List<TaskDbEntity>)

    @Transaction
    @Query("SELECT * FROM tasks")
    fun getTaskWithMembers(): List<TaskWithMembers>

}