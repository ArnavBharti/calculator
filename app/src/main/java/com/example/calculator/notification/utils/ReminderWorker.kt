package com.example.calculator.notification.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.calculator.R

class ReminderWorker(val context: Context, val params: WorkerParameters) : Worker(context, params){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        NotificationHelper(context).sendNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString()   )

        return Result.success()
    }
}