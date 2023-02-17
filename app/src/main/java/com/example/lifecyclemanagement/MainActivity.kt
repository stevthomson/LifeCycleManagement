package com.example.lifecyclemanagement

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    private var firstName: String = ""
    private var middleName: String = ""
    private var lastName: String = ""
    private var picturePath : String = ""
    private var profilePic : Bitmap? = null
    private var picIsSet : Boolean = false
    private lateinit var inputFirstName : EditText
    private lateinit var inputMiddleName : EditText
    private lateinit var inputLastName : EditText
    private lateinit var image : ImageView

    private var camera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            profilePic =result.data!!.extras!!["data"] as Bitmap?
            image = findViewById(R.id.img_profileImage) as ImageView
            image.setImageBitmap(profilePic)
            picIsSet = true

            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                picturePath = "/data/user/0/com.example.lifecyclemanagement/app_myPics/myProfilePicture.png"
                saveImage()
            } else {
                Toast.makeText(this, "Cannot save image", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

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
            picturePath = savedInstanceState.getString("picturePath", "")
            image = findViewById<ImageView>(R.id.img_profileImage)

            inputFirstName.setText(firstName)
            inputMiddleName.setText(middleName)
            inputLastName.setText(lastName)
            if(picturePath != "")
            {
                image.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                picIsSet = true
            }

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
        camera.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private fun saveImage() {
        val cw = ContextWrapper(applicationContext)
        val directory = cw.getDir("myPics", Context.MODE_PRIVATE)
        println(directory.absolutePath)
        directory.mkdir()

        val file = File(directory, picturePath)
        if(file.exists())
            file.delete()

        try {
            val outputStream = FileOutputStream(file)
            profilePic?.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
            outputStream.flush()
            outputStream.close()
        }
        catch (e :Exception)
        {
            println(e)
        }
    }


    private fun logIn(){
        if(picIsSet)
        {
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
                    intent.putExtra("picturePath",  picturePath)
                    startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                }
            }
        }
        else
        {
            Toast.makeText(applicationContext,"First take a profile picture!",Toast.LENGTH_SHORT).show()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("firstName", firstName)
        outState.putString("middleName", middleName)
        outState.putString("lastName", lastName)
        outState.putString("picturePath", picturePath)
    }
}
