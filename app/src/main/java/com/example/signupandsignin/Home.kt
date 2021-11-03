package com.example.signupandsignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.widget.TextView
import android.content.Context

class Home : AppCompatActivity() {
    lateinit var db: DBH
    lateinit var tvName : TextView
    lateinit var tvLoc : TextView
    lateinit var spf: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

         tvName = findViewById<TextView>(R.id.tvName)
         tvLoc = findViewById<TextView>(R.id.tvLoc)
        //initialize DB
         db = DBH(this)
        //initialize SharedPreferences
        spf = this.getSharedPreferences(
            getString(R.string.preference_file_key),Context.MODE_PRIVATE)

        var key = spf.getString("username","").toString()
        android.util.Log.i("      Kye value", "is : $key")

        tvName.setText("Welcome "+key+"!"+"\n")
        var data = db.getdet(key)
        android.util.Log.i("     Data value", "is : $data")
        tvLoc.setText("Your details are \n"+data+"\n")
    }
}