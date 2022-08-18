/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.runtracker

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.raywenderlich.android.runtracker.databinding.ActivityMapsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

  private lateinit var map: GoogleMap
  private lateinit var binding: ActivityMapsBinding
  private var previousLocation = LatLng(-34.0, 151.0)
  private val presenter = MapPresenter(this)
  var btnstartStop: Button? = null

  var myDB2: DatabaseHelper3? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setTheme(R.style.AppTheme)

    binding = ActivityMapsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val db = DatabaseManager()
    btnstartStop = findViewById<View>(R.id.btnStartStop) as Button


    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    val mapFragment = supportFragmentManager
      .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)
    myDB2 = DatabaseHelper3(this)

    binding.btnStartStop.setOnClickListener {
      if (binding.btnStartStop.text == getString(R.string.start_label)) {
        startTracking()
        binding.btnStartStop.setText(R.string.stop_label)
      } else {
        stopTracking()
        binding.btnStartStop.setText(R.string.start_label)
      }
    }

    presenter.onViewCreated()

  }



  override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap

    presenter.ui.observe(this) { ui ->
      updateUi(ui)
    }

    presenter.onMapLoaded()
    map.uiSettings.isZoomControlsEnabled = true
  }

  private fun startTracking() {
    binding.container.txtPace.text = ""
    binding.container.txtDistance.text = ""
    binding.container.txtTime.base = SystemClock.elapsedRealtime()
    binding.container.txtTime.start()
    map.clear()

    presenter.startTracking()
  }

  private fun stopTracking() {
    presenter.stopTracking()
    binding.container.txtTime.stop()
  }

  @SuppressLint("MissingPermission")
  private fun updateUi(ui: Ui) {
    if (ui.currentLocation != null && ui.currentLocation != map.cameraPosition.target) {
      map.isMyLocationEnabled = true
      map.animateCamera(CameraUpdateFactory.newLatLngZoom(ui.currentLocation, 14f))
    }
    binding.container.txtDistance.text = ui.formattedDistance
    binding.container.txtPace.text = ui.formattedPace
    drawRoute(ui.userPath)
  }

  private fun drawRoute(locations: List<LatLng>) {

    map.clear()
    val options = PolylineOptions().width(5f).color(Color.BLUE).geodesic(true)
    val points = options.points

    for (i in 0 until points.size) {
      val point: LatLng = points.get(i)
      options.add(point)
    }

    points.addAll(locations)

    map.addPolyline(options)
  }




}






