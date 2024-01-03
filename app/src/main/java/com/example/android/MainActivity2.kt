package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.databinding.ActivityMain2Binding
import com.example.android.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding1: ActivityMain2Binding
    private var z="123"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 =ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding1.root)
        z= intent.getStringExtra("z").toString()
        binding1.a11.alpha=0f
        binding1.a12.alpha=0f
        binding1.a13.alpha=0f
         if(z=="1"){
            binding1.a11.alpha=1f
        }else if(z=="2"){
            binding1.a12.alpha=1f
        }else if(z=="3"){
            binding1.a13.alpha=1f
        }
    }
}
