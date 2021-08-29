package com.gpaddy.baseandroid.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.gpaddy.baseandroid.databinding.ActivityLoginBinding
import com.gpaddy.baseandroid.viewmodel.LoginViewModel

import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import java.security.MessageDigest
import com.gpaddy.baseandroid.R;

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = 120
        private const val TAG = "Sang"
    }
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager:CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

//        val btn= findViewById<TextView>(R.id.btn_google)        //Firebase Auth instance
        mAuth = FirebaseAuth.getInstance()

        binding.btnGoogle.setOnClickListener {
            signIn()
        }
        callbackManager = CallbackManager.Factory.create()

        binding.btnFacebook.setOnClickListener {
//            binding.loginButton.setReadPermissions("email", "public_profile")
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "user_friends"));
        }
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.e(TAG, "facebook:onSuccess:$loginResult")
//                handleFacebookAccessToken(loginResult.accessToken)
                useLoginInformation(loginResult.accessToken)

            }

            override fun onCancel() {
                Log.e(TAG, "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.e(TAG, "facebook:onError "+ error.toString())
                // ...
            }
        })
        hisHash()

    }
      fun useLoginInformation(accessToken: AccessToken) {
        val request =
            GraphRequest.newMeRequest(
                accessToken
            ) { `object`, response ->
                //OnCompleted is invoked once the GraphRequest is successful
                try {
                    val name = `object`.getString("name")
//                    val email = `object`.getString("email")
                    val image =
                        `object`.getJSONObject("picture").getJSONObject("data").getString("url")

                    Log.e("Sang","$name, $image")
                    viewModel.addUserFb(name,name,image)
                    //Sử dụng thông tin lấy được
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        // We set parameters to the GraphRequest using a Bundle.
        val parameters = Bundle()
        parameters.putString("fields", "id,name,picture.width(200)")
        request.parameters = parameters
        // Initiate the GraphRequest
        request.executeAsync()
        openMainActivity()

    }

//    private fun handleFacebookAccessToken(token: AccessToken) {
//        Log.d(TAG, "handleFacebookAccessToken:$token")
//
//        val credential = FacebookAuthProvider.getCredential(token.token)
//        mAuth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.e(TAG, "signInWithCredential:success")
//                    val user = mAuth.currentUser
//                    viewModel.updateUI(user)
//                    openMainActivity()
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.e(TAG, "signInWithCredential:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
////                    updateUI(null)
//                }
//
//                // ...
//            }
//    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun hisHash() {
        try {
            var info=packageManager.getPackageInfo(packageName,PackageManager.GET_SIGNATURES)
            for ( sin in  info.signatures ){
                val md= MessageDigest.getInstance("SHA")
                md.update(sin.toByteArray())
                val s= Base64.encodeToString(md.digest(),Base64.DEFAULT)
                Log.e("Sang key", s)
            }
        }catch (e:Error){

        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.e("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.e("SignInActivity", "Google sign in failed", e)
                }
            } else {
                Log.e("SignInActivity", exception?.message.toString())
            }
        }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        Log.e("SignInActivity", "token: $idToken");
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.e("SignInActivity", "signInWithCredential:success")

                    val user = mAuth.currentUser
                    viewModel.updateUI(user)
                    openMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("SignInActivity", "signInWithCredential:failure")
                }
            }
    }

}