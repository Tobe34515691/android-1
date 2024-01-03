package com.example.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.GradientDrawable.Orientation
import android.hardware.Sensor
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import androidx.core.view.marginTop
import com.example.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),LocationListener {
    private lateinit var binding:ActivityMainBinding
    private lateinit var locationManager:LocationManager

    private var x=0.0
    private var y=0.0
    private var z=0
    private var q=0
    private var hasGPS:Boolean=false
    private var hasNetwork:Boolean=true


    @SuppressLint("ServiceCast", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            q=1
        }
        binding.button2.setOnClickListener {
            Intent(this,MainActivity2::class.java).apply {
                putExtra("z",z.toString())
                startActivity(this)
            }
        }
        locationManager=getSystemService(Context.LOCATION_SERVICE)as LocationManager
        hasGPS=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        binding.imageView1.alpha=0f
        binding.imageView2.alpha=0f
        binding.imageView3.alpha=0f
                if(hasGPS ||hasNetwork){
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1.0F,this)
        }
    }

    override fun onLocationChanged(p0: Location) {
        //TODO("Not yet implemented")
        if (q==1){
            x = p0.longitude.toDouble()
            y = p0.latitude.toDouble()
            z=0
            if (x > 121.4633 ) {
                if (x < 121.46400) {
                    if (y > 25.0219) {
                        if (y < 25.02244) {
                            z = 1
                        }
                    }
                }
            }
            if (x < 121.4646) {
                if (x > 121.4640) {
                    if (y > 25.0213) {
                        if (y < 25.0217) {
                            z = 2
                        }
                    }
                }
            }
            if (x < 121.4647) {
                if (x > 121.4643) {
                    if (y > 25.0207) {
                        if (y < 25.0213) {
                            z = 3 //1=綜合大樓
                        }
                    }
                }
            }

            binding.imageView1.alpha = 0f
            binding.imageView2.alpha = 0f
            binding.imageView3.alpha = 0f





            if (z == 1) {
                binding.textView.text = "綜合大樓"
                binding.imageView1.alpha = 1f
                binding.imageView1.rotation = -90f
            } else if (z == 2) {
                binding.textView.text = "和平樓"
                binding.imageView2.alpha = 1f
            } else if (z == 3) {
                binding.textView.text = "行政大樓"
                binding.imageView3.alpha = 1f
            }
        }
    }
}