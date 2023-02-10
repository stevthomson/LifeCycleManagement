package com.example.lifecyclemanagement

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoggedIn: AppCompatActivity()  {

    private var firstName: String = ""
    private var lastName: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        if(savedInstanceState != null)
            loadPrevSession(savedInstanceState)
        else
            displayWelcomeMessage()
    }

    private fun loadPrevSession(savedInstanceState: Bundle) {
        firstName = savedInstanceState.getString("firstName", "")
        lastName = savedInstanceState.getString("lastName", "")
        displayWelcomeMessage(firstName, lastName)
    }

    private fun displayWelcomeMessage(fName : String="", lName : String = "") {
        if(fName == "" || lName == "")
        {
            firstName = intent.getStringExtra("firstName").toString()
            lastName = intent.getStringExtra("lastName").toString()
        }

        val message = findViewById<TextView>(R.id.welcome_message) as TextView
        message.text = firstName + " " + lastName +" is logged in!"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("firstName", firstName)
        outState.putString("lastName", lastName)
    }
}