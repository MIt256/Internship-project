package com.example.taskmanager.wm

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.lifecycle.viewModelScope
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.taskmanager.room.RoomRepository
import com.example.taskmanager.ui.task.TaskRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val repository: RoomRepository
) :
    CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return try {
            repository.fetchTasksAndSave()
            Result.success()
        } catch (e: Throwable) {
            Log.e("SyncWorker", "Error, result is failure")
            Result.failure()
        }

    }
}