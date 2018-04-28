package com.elano.androidlifecycles.step4

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.elano.androidlifecycles.R
import kotlinx.android.synthetic.main.location_activity.*

class LocationActivity : AppCompatActivity() {

    private val mGpsListener: MyLocationListener = MyLocationListener()

    companion object {
        private const val REQUEST_LOCATION_PERMISSION_CODE = 1
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            bindLocationListener()
        else
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_SHORT).show()
    }

    private fun bindLocationListener() {
        BoundLocationManager.bindLocationListenerIn(this, mGpsListener, applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_activity)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    REQUEST_LOCATION_PERMISSION_CODE)
        else
            bindLocationListener()
    }

    inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location?) {
            val textView = tvLocation
            textView.text = ("${location?.latitude}, ${location?.longitude}")
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

        override fun onProviderEnabled(provider: String?) {
            Toast.makeText(applicationContext, "Provider enabled: $provider", Toast.LENGTH_SHORT).show()
        }

        override fun onProviderDisabled(provider: String?) {}
    }
}