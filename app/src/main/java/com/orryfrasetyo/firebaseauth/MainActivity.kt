package com.orryfrasetyo.firebaseauth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var textFullName: TextView
    lateinit var textEmail: TextView
    lateinit var btnLogout: Button

    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textFullName = findViewById(R.id.tv_full_name)
        textEmail = findViewById(R.id.tv_email)
        btnLogout = findViewById(R.id.btn_logout)

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            textFullName.text = firebaseUser.displayName
            textEmail.text = firebaseUser.email
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}












