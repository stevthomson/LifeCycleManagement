package com.example.lifecyclemanagement

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    private var firstName: String = ""
    private var middleName: String = ""
    private var lastName: String = ""
    lateinit var profilePic : Bitmap
    lateinit var inputFirstName : EditText
    lateinit var inputMiddleName : EditText
    lateinit var inputLastName : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerButtonHandlers()
        registerNameInputHandlers()
    }

    private fun registerNameInputHandlers() {
        inputFirstName = findViewById<EditText>(R.id.editFirstName) as EditText
        inputMiddleName = findViewById<EditText>(R.id.editMiddleName) as EditText
        inputLastName = findViewById<EditText>(R.id.editLastName) as EditText
    }

    private fun registerButtonHandlers() {
        val takePictureButton = findViewById<Button>(R.id.buttonTakePicture) as Button
        val logInButton = findViewById<Button>(R.id.buttonLogIn) as Button

        logInButton.setOnClickListener {
            logIn()
        }

        takePictureButton.setOnClickListener{
            takeProfilePicture()
        }
    }

    private fun takeProfilePicture() {
        TODO("Not yet implemented")
    }


    private fun logIn(){
//        if(this::profilePic.isInitialized)
//        {
//            try {
//                startActivity(Intent(this, LoggedIn::class.java))
//            } catch (ex: ActivityNotFoundException) {
//            }
//        }
//        else
//        {
//            Toast.makeText(applicationContext,"First take a profile picture!",Toast.LENGTH_SHORT).show()
//        }

        // require first and last name, but middle is optional
        firstName = inputFirstName.text.toString()
        middleName = inputMiddleName.text.toString()
        lastName = inputLastName.text.toString()

        if(firstName == "" || lastName == "")
        {
            Toast.makeText(applicationContext,"Please fill in with your First and Last Name!",Toast.LENGTH_SHORT).show()
        }
        else
        {
            try {
                println("going to different activity")
                startActivity(Intent(this@MainActivity, LoggedIn::class.java))
            } catch (ex: ActivityNotFoundException) {
            }
        }

    }
}
