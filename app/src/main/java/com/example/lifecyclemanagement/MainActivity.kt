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
    private lateinit var profilePic : Bitmap
    private lateinit var inputFirstName : EditText
    private lateinit var inputMiddleName : EditText
    private lateinit var inputLastName : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerButtonHandlers()
        registerNameInputHandlers()

        if(savedInstanceState != null)
            loadPrevSession(savedInstanceState)
    }


    private fun loadPrevSession(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            firstName = savedInstanceState.getString("firstName", "")
            middleName = savedInstanceState.getString("middleName", "")
            lastName = savedInstanceState.getString("lastName", "")

            inputFirstName.setText(firstName)
            inputMiddleName.setText(middleName)
            inputLastName.setText(lastName)
        }

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
                    val intent :Intent = Intent(this@MainActivity, LoggedIn::class.java)
                    intent.putExtra("firstName", firstName)
                    intent.putExtra("lastName", lastName)
                    startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                }
            }
//        }
//        else
//        {
//            Toast.makeText(applicationContext,"First take a profile picture!",Toast.LENGTH_SHORT).show()
//        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("firstName", firstName)
        outState.putString("middleName", middleName)
        outState.putString("lastName", lastName)
        // save profile picture too
    }
}
