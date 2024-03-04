package com.example.mapfortravelers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mapfortravelers.databinding.ActivityStartBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.MapKitFactory.setApiKey

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    private lateinit var btnSignUp: Button
    private lateinit var btnLogin: Button
    private lateinit var btnGuest: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityStartBinding.inflate(layoutInflater) // Раздуваем макет только после того, как установили API-ключ
        setContentView(binding.root) // Размещаем пользовательский интерфейс в экране активности

        btnSignUp = findViewById(R.id.signUpButton)
        btnLogin = findViewById(R.id.loginButton)
        btnGuest = findViewById(R.id.guestButton)

        btnSignUp.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent);
        }
        btnLogin.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent);
        }
        btnGuest.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }
    }
}