package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.content.SharedPreferences

class Sign_In : AppCompatActivity() {

    lateinit var etNmae : EditText
    lateinit var etPassword : EditText
    lateinit var btn : Button
    private lateinit var dbhr: DBH
    lateinit var spf: SharedPreferences
    lateinit var editr: SharedPreferences.Editor
    lateinit var pw: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etNmae = findViewById(R.id.etName)
        etPassword = findViewById(R.id.etPassword)
        btn = findViewById(R.id.btn)
        //initialize SharedPreferences
        spf = this.getSharedPreferences(
            getString(R.string.preference_file_key), android.content.Context.MODE_PRIVATE)
        editr = spf.edit()
        //initialize DB
        dbhr = DBH(this)

       btn.setOnClickListener{
           var nameToCheck = etNmae.text.toString()
           var passToCheck = etPassword.text.toString()
           pw = dbhr.checkpassword(nameToCheck)
           if (pw.equals(passToCheck)){
               android.util.Log.i("Check password state:", "success login")
               editr.putString("username", nameToCheck).commit()
               var intent = android.content.Intent(this, Home::class.java)
               startActivity(intent)
           } else{
               android.util.Log.i("Check password state:", "Failed to login")
           }
       }

    }
}