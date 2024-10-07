package com.thangavel.sqlite.helper

import android.content.Context
import android.widget.Toast

class HelperFunctions {

    companion object {
        fun showShortToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}