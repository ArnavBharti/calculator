package com.example.calculator.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.calculator.R
import com.example.calculator.databinding.ActivityHistoryBinding
import com.example.calculator.databinding.ActivityNotificationBinding
import com.example.calculator.history.History
import com.example.calculator.notification.utils.NotificationHelper
import com.example.calculator.notification.utils.ReminderWorker
import com.example.calculator.standard_calculator.Calculate
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

class NotificationMain : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    lateinit var binding: ActivityNotificationBinding
    lateinit var holder: ActivityHistoryBinding

    private val CHANNEL_ID = "channel_id_1"
    private val NOTIFICATION_ID = 101

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Notification Sender"


        binding.buttonDel3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}".dropLast(1)
        }
        binding.buttonOne3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}1"
        }
        binding.buttonTwo3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}2"
        }
        binding.buttonThree3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}3"
        }
        binding.buttonFour3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}4"
        }
        binding.buttonFive3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}5"
        }
        binding.buttonSix3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}6"
        }
        binding.buttonSeven3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}7"
        }
        binding.buttonEight3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}8"
        }
        binding.buttonNine3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}9"
        }
        binding.buttonZero3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}0"
        }
        binding.buttonAddition3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}+"
        }
        binding.buttonSubtraction3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}-"
        }
        binding.buttonMultiplication3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}*"
        }
        binding.buttonDivision3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}/"
        }
        binding.buttonPower3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}^"
        }


        binding.buttonEqualsSign3.setOnClickListener {
            val answer = Calculate().calculate(binding.mainView3.text.toString())
            binding.outputBox3.text = answer
            if (answer.lowercase() != "error") {
                val delayInSeconds = answer.slice(0 until answer.indexOf('.')).toLong()
                createWorkRequest("Answer Timer", delayInSeconds)

            }
        }

        binding.buttonOpenParenthesis3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}("
        }
        binding.buttonCloseParenthesis3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text})"
        }
        binding.buttonDecimal3.setOnClickListener {
            binding.mainView3.text = "${binding.mainView3.text}."
        }
        binding.buttonAllClear3.setOnClickListener {
            binding.mainView3.text = ""; binding.outputBox3.text = ""
        }
        binding.buttonHistory3.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
        binding.buttonMatrix2.setOnClickListener {
            onBackPressed()
        }


    }


    private fun createWorkRequest(message: String, timeDelayInSeconds: Long) {
        val myWorkRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(timeDelayInSeconds, TimeUnit.SECONDS)
            .setInputData(
                workDataOf(
                    "title" to "Reminder",
                    "message" to message,
                )
            )
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)
    }

}


