package com.example.tipmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cost of Service variable that will be used to calculate the tip
        // 1. Create a variable to hold the cost of service
        // 2. Another variable to hold the cost of service and calculate with the tip
        //  2.1 Variable to each tip? Based on Satisfaction
        // 3. Onclick action/variable to calculate the tip
        //   3.1 Makes a toast "congrats"
        // 4. Print the TIP value on textview.

        niceTipSnackBar()
    }


}