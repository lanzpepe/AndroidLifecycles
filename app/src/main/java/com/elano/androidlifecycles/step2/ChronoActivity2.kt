package com.elano.androidlifecycles.step2

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.widget.Chronometer
import com.elano.androidlifecycles.R

class ChronoActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel::class.java)
        val chronometer = findViewById<Chronometer>(R.id.chronometer)

        if (chronometerViewModel.mStartTime == null) {
            val startTime = SystemClock.elapsedRealtime()
            chronometerViewModel.mStartTime = startTime
            chronometer.base = startTime
        }
        else
            chronometer.base = chronometerViewModel.mStartTime!!

        chronometer.start()
    }
}