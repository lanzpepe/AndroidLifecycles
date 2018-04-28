package com.elano.androidlifecycles.step3

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.elano.androidlifecycles.R
import kotlinx.android.synthetic.main.chrono_activity_3.*

class ChronoActivity3 : AppCompatActivity() {

    private lateinit var mLiveDataTimerViewModel: LiveDataTimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chrono_activity_3)
        mLiveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel::class.java)
        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long> {
            val newText = this.resources.getString(R.string.text_seconds, it)
            tvTimer.text = newText
            Log.d("ChronoActivity3", "Updating Timer")
        }

        mLiveDataTimerViewModel.mElapsedTime?.observe(this, elapsedTimeObserver)
    }
}