package com.example.mapfortravelers

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import io.ktor.client.HttpClient
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.http.Parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection


class LoginActivity : AppCompatActivity() {

    private lateinit var redirectSignUp: TextView
    lateinit var username: EditText
    private lateinit var password: EditText
    lateinit var btnLogin: Button

    // Creating firebaseAuth object
    //lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // View Binding
        redirectSignUp = findViewById(R.id.redirectSignUp)
        btnLogin = findViewById(R.id.loginButton)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

        // initialising Firebase auth object
        //auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }

        redirectSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }
    }

    private fun login() {
        val name = username.text.toString()
        val pass = password.text.toString()

        // Add URL parameters
        val uriBuilder = Uri.Builder()
            .appendQueryParameter("grant_type", "")
            .appendQueryParameter("username", name)
            .appendQueryParameter("password", pass)
            .appendQueryParameter("scope", "")
            .appendQueryParameter("client_id", "")
            .appendQueryParameter("client_secret", "")
            .build()

        val params = uriBuilder.toString().replace("?", "")  // Remove the "?" from the beginning of the parameters ?name=Jack&salary=8054&age=45
        val postData = params.toByteArray(StandardCharsets.UTF_8)

        GlobalScope.launch(Dispatchers.IO) {
            val url = URL("https://maps.rtuitlab.dev/api/auth/jwt/login")
            val httpsURLConnection = url.openConnection() as HttpsURLConnection
            httpsURLConnection.requestMethod = "POST"
            httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded") // The format of the content we're sending to the server
            httpsURLConnection.setRequestProperty("Accept", "application/json") // The format of response we want to get from the server
            httpsURLConnection.doInput = true
            httpsURLConnection.doOutput = true
            val dataOutputStream = DataOutputStream(httpsURLConnection.outputStream)
            dataOutputStream.write(postData)

            // Check if the connection is successful
            val responseCode = httpsURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //flag = true
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent);
                val response = httpsURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
                withContext(Dispatchers.Main) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(JsonParser.parseString(response))
                    Log.d("Pretty Printed JSON :", prettyJson)

                }
            } else {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(this@LoginActivity, "Log In failed", Toast.LENGTH_SHORT).show()
                }
                Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
            }
        }



    }

}