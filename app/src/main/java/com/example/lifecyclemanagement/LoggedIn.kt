package com.example.lifecyclemanagement

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoggedIn: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        displayWelcomMessage()
    }

    private fun displayWelcomMessage() {
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")

        val message = findViewById<TextView>(R.id.welcome_message) as TextView
        message.text = firstName + " " + lastName +" is logged in!"
    }
}