package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn1 = findViewById<Button>(R.id.btn_signIn)
        btn1.setOnClickListener{
            var intent = Intent(this, Sign_In :: class.java)
                         startActivity(intent)
        }
        var btn2 = findViewById<Button>(R.id.btn_SignUp)
        btn2.setOnClickListener{
            var intent = Intent(this, SignUp :: class.java)
            startActivity(intent)
        }
    }
}