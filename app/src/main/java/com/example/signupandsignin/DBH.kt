package com.example.signupandsignin


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBH(context: Context): SQLiteOpenHelper(context, "MyDB3", null, 1) {

    var db : SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        // 1 create table
        db?.execSQL("CREATE TABLE Users_Table ( ID INTEGER PRIMARY KEY, Name TEXT , Phone TEXT, Loc TEXT, Password TEXT )")
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){}
    //2 save data
    fun save( name: String , phone: String, loc: String ,pass: String): Long {
        db = writableDatabase
        val cv = ContentValues()
        cv.put("Name" , name)
        cv.put("Phone" , phone)
        cv.put("Loc" , loc)
        cv.put("Password" , pass)
        val status = db.insert("Users_Table", null, cv)
        return status
    }
    //3 retrieve data
    fun getdet(username: String?): String {
        val db = this.readableDatabase
        var c: Cursor = db.query("Users_Table", null, "Name=?", arrayOf(username), null, null, null)
        if (c.count < 1) {
            return "name not exists"
        }
        c.moveToFirst()
        var data = c.getString(c.getColumnIndex("Name"))+
                "\n"+ c.getString(c.getColumnIndex("Phone"))+"\n"+ c.getString(c.getColumnIndex("Loc"))
        return data
    }
    //3 check data
    fun checkpassword(username: String): String {

        var c: Cursor = db.query("Users_Table", null, "Name=?", arrayOf(username), null, null, null)
        if (c.count < 1) {
            return "name not exists"
        }
        c.moveToFirst()
        var password = c.getString(c.getColumnIndex("Password"))
        return password
    }

}