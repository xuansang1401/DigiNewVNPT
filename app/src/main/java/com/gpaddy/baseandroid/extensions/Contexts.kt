package com.gpaddy.baseandroid.extensions

import android.content.Context
import android.widget.Toast


fun Context.showToast(context: String) {
    Toast.makeText(this, context, Toast.LENGTH_LONG).show();
}