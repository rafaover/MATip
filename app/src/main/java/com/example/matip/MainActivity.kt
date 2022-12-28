package com.example.matip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.matip.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting a keylistener to InputText to hide the keyboard
        // when the user presses the enter key
        binding.costOfServiceEditText.setOnKeyListener {
                view, keyCode, _ -> enterKeyEvent(view, keyCode)
        }

        binding.calculateButton.setOnClickListener {
            hideSoftInput(view = it)
            calculateTip()
        }
    }

    // Event when enter key is pressed
    fun enterKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            hideSoftInput(view)
            return true
        }
        return false
    }

    // Hide soft keyboard
    fun hideSoftInput(view: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun costOfService(): Double {
        // getting the text attribute from costOfService(EDITTEXT)
        // toString() added because EDITTEXT data type is EDIBLE, not String.
        val stringCostOfService = binding.costOfServiceEditText.text.toString()
        val doubleCostOfService = stringCostOfService.toDoubleOrNull()
        if (doubleCostOfService == null || doubleCostOfService == 0.0) {
            noInputSnackBar()
            displayTip(0.0)
        } else niceTipSnackBar()
        return doubleCostOfService ?: 0.0
    }

    // Format the tip value for the local currency
    fun displayTip(Tip: Double) {
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(Tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    fun tipPercentage() {
        // Binding the RadioButtonGroup and getting the selected RadioButton (TIP %)
        val tipPercentOption = binding.tipOptions.checkedRadioButtonId
        // Getting the right Tip percentage
        val tipSelected = when(tipPercentOption) {
            R.id.percent_option_20 -> 0.20
            R.id.percent_option_15 -> 0.15
            R.id.percent_option_10 -> 0.10
            else -> 0.0
        }
        var finalTip = tipSelected * costOfService()
        // Tip rounding Up
        if (binding.roundUpSwitch.isChecked) {
            finalTip = kotlin.math.ceil(finalTip)
        }
        displayTip(finalTip)
    }

    fun calculateTip() {
        costOfService()
        tipPercentage()
    }

    // SNACKBARS BELOW
    fun niceTipSnackBar() {
        val snackListPos = listOf(
            "Well Done!", "Let's Roll", "Hell Yeah!", "Noice!"
        )
        Snackbar.make(findViewById(R.id.calculate_button),
            snackListPos.random(), Snackbar.LENGTH_SHORT).show()

    }

    fun noInputSnackBar() {
        Snackbar.make(findViewById(R.id.calculate_button),
            "Add a real value, mate!", Snackbar.LENGTH_SHORT).show()
    }
}