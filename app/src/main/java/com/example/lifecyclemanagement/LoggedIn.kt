package com.example.lifecyclemanagement

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoggedIn: AppCompatActivity()  {

    private var firstName: String = ""
    private var lastName: String = ""
    private var picturePath : String = ""
    private lateinit var image : ImageView
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
        picturePath = savedInstanceState.getString("picturePath", "")
        displayWelcomeMessage(firstName, lastName)
    }


    private fun displayWelcomeMessage(fName : String="", lName : String = "") {
        if(fName == "" || lName == "")
        {
            firstName = intent.getStringExtra("firstName").toString()
            lastName = intent.getStringExtra("lastName").toString()
            picturePath = intent.getStringExtra("picturePath").toString()
        }

        val message = findViewById<TextView>(R.id.welcome_message)
        message.text = firstName + " " + lastName +" is logged in!"
        println(picturePath)
        image = findViewById<ImageView>(R.id.img_profileImage)
        image.setImageBitmap(BitmapFactory.decodeFile(picturePath))
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("firstName", firstName)
        outState.putString("lastName", lastName)
        outState.putString("picturePath", picturePath)
    }
}