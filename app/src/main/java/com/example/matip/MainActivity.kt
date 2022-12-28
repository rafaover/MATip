package com.example.matip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.matip.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting a keylistener to InputText to hide the keyboard when the user
        // presses the enter key
        binding.costOfServiceEditText.setOnKeyListener {
                view, keyCode, _ -> keyboardKeyEvent(view, keyCode)
        }

        // Binding calculateButton(Button) to a ClickListener and
        // executing the calculate() method
        binding.calculateButton.setOnClickListener {
            hideSoftInput(view = it)
            calculateTip()
            niceTipSnackBar()
        }
    }

    // function to hide the keyboard when enter key is pressed
    fun keyboardKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            hideSoftInput(view)
            return true
        }
        return false
    }

    // Hide the keyboard
    fun hideSoftInput(view: View){
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun costOfService(): Double {
        // getting the text attribute from costOfService(EDITTEXT)
        // toString() added because EDITTEXT data type is EDIBLE, not String.
        val stringCostOfService = binding.costOfServiceEditText.text.toString()
        val doubleCostOfService = stringCostOfService.toDoubleOrNull()
        while (doubleCostOfService == null || doubleCostOfService == 0.0) {
            Snackbar.make(findViewById(R.id.calculate_button),
                "I NEED A NUMBER, MATE", Snackbar.LENGTH_SHORT).show()
            displayTip(0.0)
        }
        return doubleCostOfService ?: 0.0
    }

    // Function to calculate the TIP when value is added and button pushed
    private fun calculateTip(){

        costOfService()
        
        //Binding the RadioButtonGroup and getting the selected RadioButton (TIP %)
        val tipPercentOption = binding.tipOptions.checkedRadioButtonId

        // Getting the right Tip percentage
        val tipSelected = when(tipPercentOption) {
            R.id.percent_option_20 -> 0.20
            R.id.percent_option_15 -> 0.15
            R.id.percent_option_10 -> 0.10
            else -> 0.0
        }
        // Final TIP calculation
        var finalTip = tipSelected * costOfService()
        // Tip rounding Up
        if (binding.roundUpSwitch.isChecked) {
            finalTip = kotlin.math.ceil(finalTip)
        }
        // Formatting the final TIP result
        displayTip(finalTip)
    }

    // Function to format the tip value for the local currency
    private fun displayTip(Tip: Double) {
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(Tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    // Method to show a snackbar at the bottom when the user gives the tip.
    fun niceTipSnackBar() {
        val snackListPos = listOf(
            "Well Done!", "Let's Roll", "Hell Yeah!", "Noice!"
        )
        Snackbar.make(
            findViewById(R.id.calculate_button), snackListPos.random(), Snackbar.LENGTH_SHORT
        ).show()

    }
}