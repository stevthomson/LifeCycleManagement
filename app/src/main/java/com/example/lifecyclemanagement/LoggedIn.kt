package com.example.lifecyclemanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoggedIn: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        println("hello from logged in activity")
    }
}