package com.example.muvies.utils

import android.app.Activity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun Fragment.showShortSnackBar(message: String) {
    val snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
    val snackBarView: View = snackBar.view
    val snackTextView: TextView =
        snackBarView.findViewById(com.google.android.material.R.id.snackbar_text)
    snackTextView.maxLines = 3
    snackBar.show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
