package com.elano.androidlifecycles.step3

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import java.util.*

/**
 * A ViewModel used for the {@link ChronoActivity3}.
 */

class LiveDataTimerViewModel(var mElapsedTime: MutableLiveData<Long>? = null) : ViewModel() {

    private var mInitialTime: Long = 0

    init {
        mElapsedTime = MutableLiveData()
        mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                // setValue() cannot be called from a background thread so post to main thread.
                mElapsedTime!!.postValue(newValue)
            }
        }, ONE_SECOND, ONE_SECOND)

    }

    companion object {
        private const val ONE_SECOND = 1000L
    }
}