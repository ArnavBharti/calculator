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
import com.example.calculator.R
import com.example.calculator.databinding.ActivityHistoryBinding
import com.example.calculator.databinding.ActivityNotificationBinding
import com.example.calculator.history.History
import com.example.calculator.standard_calculator.Calculate
import java.time.LocalDateTime
import java.util.*

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

        createNotificationChannel()

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
            if (answer != "error") {
                sendNotification(answer)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description=descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

    private fun sendNotification(answer: String) {

        val intent = Intent(this, NotificationMain::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_IMMUTABLE )


        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_launcher_background)
        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_launcher_foreground)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_history_24)
            .setContentTitle("Answer Timer")
            .setContentText("$answer seconds are up!")
            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }

    }


}


