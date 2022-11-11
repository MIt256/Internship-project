package com.example.taskmanager.wm

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class SyncWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters
) :
    CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {

        //work
        return Result.success()
    }

}
