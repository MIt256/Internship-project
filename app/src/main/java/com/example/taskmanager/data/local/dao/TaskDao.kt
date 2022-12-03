package com.example.taskmanager.data.local.dao

import androidx.room.*
import com.example.taskmanager.data.local.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TaskDao {
    @Query("SELECT * FROM tasks WHERE owner_id = :ownerId ")
    abstract fun getTasks(ownerId: String): Flow<List<TaskDbEntity?>>

    //todo check
    @Query("SELECT * FROM users, task_members WHERE users.id = task_members.user_id AND task_members.task_id = :taskId")
    abstract fun getTaskMembers(taskId: String): Flow<List<UserDbEntity?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addTask(task: TaskDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAllTasks(tasks: List<TaskDbEntity>)

    @Transaction
    @Query("SELECT * FROM tasks WHERE owner_id = :ownerId")
    abstract suspend fun getTaskWithMembers(ownerId: String): List<TaskWithMembers>

    @Query("SELECT * FROM tasks WHERE owner_id = :ownerId")
    abstract suspend fun getUserTasks(ownerId: String): List<TaskDbEntity>
}