package com.elano.androidlifecycles.step5


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar

import com.elano.androidlifecycles.R

/**
 * Shows a SeekBar that is synced with a value in a ViewModel
 */
class Fragment_step5 : Fragment(), Observer<Int>, SeekBar.OnSeekBarChangeListener {

    private var mSeekBar: SeekBar? = null
    private var mSeekBarViewModel: SeekBarViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_step5, container, false)

        mSeekBar = rootView.findViewById(R.id.seekBar)
        mSeekBarViewModel = ViewModelProviders.of(activity!!).get(SeekBarViewModel::class.java)

        // Update the ViewModel when the SeekBar is changed.
        mSeekBar?.setOnSeekBarChangeListener(this)

        subscribeSeekBar()

        return rootView
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            Log.d("Step5", "Progress changed!")
            mSeekBarViewModel?.seekBarValue?.value = progress
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    private fun subscribeSeekBar() {
        // Update the SeekBar when the ViewModel is changed.
        mSeekBarViewModel?.seekBarValue?.observe(activity!!, this)
    }

    override fun onChanged(t: Int?) {
        if (t != null)
            mSeekBar?.progress = t
    }
}
