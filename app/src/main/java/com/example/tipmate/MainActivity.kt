package com.example.tipmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var costOfServiceUser = findViewById<TextView>(R.id.cost_of_Service)


        // Executes function
        calculate()

    }
    // Function to calculate the TIP when value is added and button pushed
    private fun calculate(costOfService: Float): Unit{
    }
}