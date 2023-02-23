package com.example.trabajo_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabajo_final.databinding.ActivitySecondBinding
import com.example.trabajo_final.databinding.ActivityThirdBinding

class Third_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    acciones()



    }

    private fun acciones() {
        binding.bntAtras.setOnClickListener {
            val mainActivity= Intent(this,MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }
    }
}