package com.example.tipmate

import com.google.android.material.snackbar.Snackbar

// Snack bar to congratulate the Tip
fun niceTipSnackBar() {

    val snackListPos = listOf(
        "Well Done!", "Let's Roll", "That's what you got", "Hell Yeah!", "Noice!"
    )

    Snackbar.make(TODO("button that calculates"), snackListPos.random(), Snackbar.LENGTH_SHORT).show()
}
