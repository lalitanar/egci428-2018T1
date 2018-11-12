package com.egco428.ex20_currentlocation

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private  var locationManager: LocationManager? = null
    private var listener: LocationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        listener = object: LocationListener{
            override fun onLocationChanged(location: Location) {

                textView.append("\n"+location.latitude+"  " +location.longitude)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }
        }
        requestLocationService()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permission: Array<String>, grantResults: IntArray){
    when(requestCode){
        10-> requestLocationService()
        else ->{}
        }
    }
    fun requestLocationService(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET), 10)
            }
            return
        }



        gpsBtn.setOnClickListener {
            locationManager!!.requestLocationUpdates("gps", 5000,0f, listener)
        }
    }
}
