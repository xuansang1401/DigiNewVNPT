package com.gpaddy.baseandroid.extensions

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showToast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_LONG).show();
}
fun AppCompatActivity.penUrlInBrowser(url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}