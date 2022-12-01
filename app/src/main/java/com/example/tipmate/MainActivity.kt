package com.example.tipmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.tipmate.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Binding calculateButton(Button) to a ClickListener and
        // executing the calculate() method
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    // Function to calculate the TIP when value is added and button pushed
    fun calculateTip(){
        // getting the text attribute from costOfService(EDITTEXT)
        // The toString() was added because the data type in a EDITTEXT is an EDIBLE not String.
        val stringCostOfService = binding.costOfService.text.toString()

        val doubleCostOfService = stringCostOfService.toDoubleOrNull()
        if (doubleCostOfService == null) {
            Snackbar.make(findViewById(R.id.calculate_button), "I NEED A NUMBER, MATE", Snackbar.LENGTH_SHORT).show()
            return
        }


        //Binding the RadioButtonGroup and getting the selected RadioButton
        val tipPercentOption = binding.tipOptions.checkedRadioButtonId

        // Getting the right Tip percentage
        val tipSelected = when(tipPercentOption) {
            R.id.percent_option_20 -> 0.20
            R.id.percent_option_15 -> 0.15
            R.id.percent_option_10 -> 0.10
            else -> 0.0
        }
        // Final TIP calculation
        var finalTip = tipSelected * doubleCostOfService

        // Tip rounding Up
        val roundUpTip = binding.roundUpSwitch.isChecked
        if (roundUpTip) { finalTip = kotlin.math.ceil(finalTip) }

        // Formating the tip value for the local currency
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(finalTip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        niceTipSnackBar()
    }

    // Method to show a snackbar at the bottom when the user gives the tip.
    fun niceTipSnackBar() {
        val snackListPos = listOf(
            "Well Done!", "Let's Roll", "Hell Yeah!", "Noice!"
        )
        Snackbar.make(findViewById(R.id.calculate_button), snackListPos.random(), Snackbar.LENGTH_SHORT).show()
    }
}