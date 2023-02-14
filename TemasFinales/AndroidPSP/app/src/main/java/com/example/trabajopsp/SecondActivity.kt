package com.example.trabajopsp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabajopsp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var secondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(secondBinding.root)


    }
}