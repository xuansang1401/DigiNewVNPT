package com.gpaddy.baseandroid.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.gpaddy.baseandroid.util.AppContain
import com.pixplicity.easyprefs.library.Prefs

import dagger.hilt.android.qualifiers.ApplicationContext

class LoginViewModel @ViewModelInject constructor(
//    val appConfig: AppConfig,
    @ApplicationContext val context: Context
) : ViewModel(){

    fun updateUI(user: FirebaseUser?) {
        Log.e("Sang", user?.photoUrl.toString())
        Prefs.putString(AppContain.USER_ID,user?.uid)
        Prefs.putString(AppContain.USER_NAME,user?.displayName)
        Prefs.putString(AppContain.USER_AVATAR,user?.photoUrl.toString())
        Prefs.putString(AppContain.PHONE,user?.phoneNumber)


        Prefs.putBoolean(AppContain.IS_LOGIN,true)
    }

    fun addUserFb(id: String, name: String, avatar: String) {
        Prefs.putString(AppContain.USER_ID,id)
        Prefs.putString(AppContain.USER_NAME,name)
        Prefs.putString(AppContain.USER_AVATAR,avatar)

        Prefs.putBoolean(AppContain.IS_LOGIN,true)

    }
}