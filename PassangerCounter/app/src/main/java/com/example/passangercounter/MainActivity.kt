package com.example.passangercounter

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.passangercounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var numberPassangers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.count.text = numberPassangers.toString()
        binding.subtract.isEnabled = false
        binding.reset.visibility = View.INVISIBLE

        binding.subtract.setOnClickListener {
            if(numberPassangers > 1) {
                numberPassangers--
                binding.add.isEnabled = true
            } else if(numberPassangers == 1) {
                numberPassangers--
                binding.subtract.isEnabled = false
            }

            binding.count.text = numberPassangers.toString()
            changeState(binding)
        }

        binding.add.setOnClickListener {
            if(numberPassangers < 49) {
                numberPassangers++
                binding.subtract.isEnabled = true
            } else if(numberPassangers == 49) {
                numberPassangers++
                binding.add.isEnabled = false
                binding.reset.visibility = View.VISIBLE
            }

            binding.count.text = numberPassangers.toString()
            changeState(binding)
        }

        binding.reset.setOnClickListener {
            numberPassangers = 0
            binding.count.text = numberPassangers.toString()
            changeState(binding)
        }
    }

    fun changeState(binding: ActivityMainBinding) {
        if(numberPassangers == 0) {
            binding.state.text = "Все места свободны"
            binding.state.setTextColor(Color.parseColor("#00FF00"))
        } else if(numberPassangers < 50) {
            binding.state.text = "Осталось мест: $numberPassangers"
            binding.state.setTextColor(Color.parseColor("#0000FF"))
        } else {
            binding.state.text = "Пассажиров слишком много!"
            binding.state.setTextColor(Color.parseColor("#FF0000"))
        }
    }
}