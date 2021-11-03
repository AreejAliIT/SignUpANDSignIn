package com.example.signupandsignin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUp : AppCompatActivity() {

    lateinit var etNmae : EditText
    lateinit var etPhone: EditText
    lateinit var etLoc : EditText
    lateinit var etPassword : EditText
    lateinit var btn : Button
    private lateinit var dbhr: DBH
    lateinit var spf: SharedPreferences
    lateinit var editr : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        //initialize var
        etNmae = findViewById(R.id.etNamee)
        etPhone = findViewById(R.id.etPhone)
        etLoc = findViewById(R.id.etLoc)
        etPassword = findViewById(R.id.etPass)
        btn = findViewById(R.id.btn2)
        //initialize SharedPreferences
        spf = this.getSharedPreferences(
              getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        editr = spf.edit()
        //initialize DB
        dbhr = DBH(this)
        btn.setOnClickListener {
            var nameToSave = etNmae.text.toString()
            var phoneToSave = etPhone.text.toString()
            var locToSave = etLoc.text.toString()
            var passToSave = etPassword.text.toString()

            var status = dbhr.save(nameToSave, phoneToSave, locToSave, passToSave)
            if (status.equals("-1")) {
                Log.i("DB Status", "Failed to Save : $status")
            } else {
                Log.i("DB Status", "Saved : $status")
                //save phone as key
                editr.putString("username", nameToSave).commit()
            }
            var intent =  Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}