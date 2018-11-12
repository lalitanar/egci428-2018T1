package com.egci428.basicmap

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.egci428.basicmap.R.mipmap.ic_launcher
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.PolygonOptions







class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var lastLatLng:LatLng = LatLng(13.7934, 100.3225)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*private fun setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
                    .getMap()
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap()
            }
        }
    }*/

    private fun setUpMap(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(15.0, 100.0), 8f))
        mMap.addMarker(MarkerOptions().position(LatLng(13.7934, 100.3225)).title("Mahidol"))


        mMap.setOnMapClickListener { latLng -> mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng)) }

        //mMap.setOnMapLongClickListener { latLng -> mMap.addMarker(MarkerOptions().position(latLng).title(latLng.toString())) }

        //*** Alternative LongClick -- For student practice ***//
        mMap.setOnMapLongClickListener {
            latLng -> mMap.addMarker(MarkerOptions().position(latLng).title(latLng.toString()))
            val drawLine = mMap.addPolyline(PolylineOptions()
                    .add(lastLatLng,latLng)
                    .width(5f)
                    .color(Color.GREEN))
            lastLatLng = latLng

        }

        //Custom Markers
       /* mMap.addMarker(MarkerOptions()
                .position(LatLng(15.1123, 100.05612))
                .title("Nomal Marker")
                .snippet("content"))

        mMap.addMarker(MarkerOptions()
                .position(LatLng(15.4231, 100.45612))
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                // .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                // .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                .title("Nomal Color Marker")
                .snippet("content"))

        mMap.addMarker(MarkerOptions()
                .position(LatLng(15.0, 100.45612))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("Nomal Green Marker")
                .snippet("content"))

        mMap.addMarker(MarkerOptions()
                .position(LatLng(15.55234, 100.0))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .title("Custom Marker")
                .snippet("content"))*/

        /*// Add a thin red line in Thai Map.
        val line = mMap.addPolyline(PolylineOptions()
                .add(LatLng(15.0, 100.45612), LatLng(15.55234, 100.0), LatLng(15.4231, 100.45612))
                .width(5f)
                .color(Color.RED))

        // Add a triangle in Thai Map
        val polygon = mMap.addPolygon(PolygonOptions()
                .add(LatLng(15.0, 100.45612), LatLng(15.0, 100.7), LatLng(15.5, 100.7), LatLng(15.0, 100.45612))
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE))*/







        //https://developers.google.com/android/reference/packages
    }

    override fun onMapReady(googleMap: GoogleMap) {
        setUpMap(googleMap)

        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }
}
