package com.wallia.testmultiplica.viewmodel

import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MyViewModel : ViewModel() {
    val count: MutableLiveData<String> = MutableLiveData()

    fun doCount(minutes: Int, seconds: Int) {

        val longMinutes = minutes * 60000
        val longSeconds = seconds * 1000

        val totalTime = longMinutes.toLong() + longSeconds.toLong()

        viewModelScope.launch {
            val timer = object : CountDownTimer(totalTime, 1000) {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onTick(millisUntilFinished: Long) {
                    val formatter = DateTimeFormatter.ofPattern("mm:ss")
                    val instant = Instant.ofEpochMilli(millisUntilFinished)
                    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

                    count.value = formatter.format(date)
                }

                override fun onFinish() {

                }
            }
            timer.start()
        }
    }
}