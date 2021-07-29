package com.gpaddy.baseandroid.extensions

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(content: String) {
    context?.let {  Toast.makeText(it, content, Toast.LENGTH_LONG).show(); }
}
fun Fragment.openUrlInBrowser(url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}