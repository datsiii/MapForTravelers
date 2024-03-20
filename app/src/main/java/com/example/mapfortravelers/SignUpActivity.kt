package com.example.mapfortravelers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import org.openapitools.client.apis.AuthApi




import org.openapitools.client.models.BearerResponse
//import org.openapitools.client.models.ClientId
import org.openapitools.client.models.ClientSecret
import org.openapitools.client.models.ErrorModel
import org.openapitools.client.models.GrantType
import org.openapitools.client.models.HTTPValidationError
import org.openapitools.client.models.UserCreate
import org.openapitools.client.models.UserRead
import retrofit2.Retrofit
import java.io.IOException


class SignUpActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var confPass: EditText
    private lateinit var password: EditText
    private lateinit var btnSignUp: Button
    lateinit var redirectLogin: TextView
    private lateinit var username: TextView

    // create Firebase authentication object
    //private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // View Bindings
        email = findViewById(R.id.emailAddress)
        confPass = findViewById(R.id.password)
        password = findViewById(R.id.confPassword)
        btnSignUp = findViewById(R.id.signUpButton)
        redirectLogin = findViewById(R.id.redirectLogin)
        username = findViewById(R.id.username)

        // Initialising auth object
        //auth = Firebase.auth

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        // switching from signUp Activity to Login Activity
        redirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }


    private fun signUpUser() {
        val email = email.text.toString()
        val pass = password.text.toString()
        val confirmPassword = confPass.text.toString()
        val name = username.text.toString()

        val apiInstance = AuthApi()


        // uncomment below to test authJwtLoginApiAuthJwtLoginPost
        //val username : kotlin.String = username_example // kotlin.String |
        //val password : kotlin.String = password_example // kotlin.String |
        //val grantType : GrantType =  // GrantType |
        //val scope : kotlin.String = scope_example // kotlin.String |
        //val clientId : ClientId =  // ClientId |
        //val clientSecret : ClientSecret =  // ClientSecret |
        //val result : BearerResponse = apiInstance.authJwtLoginApiAuthJwtLoginPost(username, password, grantType, scope, clientId, clientSecret)




        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }






        val jsonObject = JSONObject()
        jsonObject.put("username", name)
        jsonObject.put("password", pass)
        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()
        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        /*val jsonRequest = "$name : String \n $pass :String"
        val JSON = "application/json; charset=utf-8".toMediaType()*/

        val client_s = OkHttpClient()

        val body: RequestBody = requestBody
        val request_s = Request.Builder().url("https://maps.rtuitlab.dev/api/auth/register").post(body).build()
        client_s.newCall(request_s).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw IOException("Запрос к серверу не был успешен:" +
                                " ${response.code} ${response.message}")
                    }
                    // пример получения всех заголовков ответа
                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }
                    // вывод тела ответа
                    println(response.body!!.string())
                }
            }
        })



        /*val userCreate : UserCreate =  UserCreate(name, pass)
        val result : UserRead = apiInstance.registerRegisterApiAuthRegisterPost(userCreate)*/
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent);

    }
}