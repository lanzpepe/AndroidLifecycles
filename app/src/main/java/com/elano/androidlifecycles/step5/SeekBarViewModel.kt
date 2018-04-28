package com.elano.androidlifecycles.step5

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * A ViewModel used in step 5
 */

class SeekBarViewModel : ViewModel() {
    val seekBarValue = MutableLiveData<Int>()
}