package com.example.tuwaiqproject2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class UserDatabase(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        // DB info
        const val DATABASE_NAME = "user_database"
        const val DATABASE_VERSION = 1

        //Table info
        const val TABLE_NAME = "user_table"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_USERNAME = "username"

    }

    fun insertUser(user: Users) {
//        writableDatabase
//        readableDatabase
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, user.name)
        values.put(COLUMN_ID , user.num)
        values.put(COLUMN_EMAIL, user.email)
        db.insert(TABLE_NAME, null, values)
        // NULL COLUMN HACK  -> this is when i don't insert value (i.e we didn't insert ID) it will
        //check if it's a primary key and auto increment otherwise leaves it as NULL
    }

    override fun onCreate(db: SQLiteDatabase) {
        val initTable = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID TEXT PRIMARY KEY," +
                " $COLUMN_NAME TEXT," +
                " $COLUMN_EMAIL TEXT)"

        db.execSQL(initTable)
    }

    fun getUsers(): List<Users> {
        val users = arrayListOf<Users>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME  "

        try {
            val cursor = db.rawQuery(query, null)
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                val userinfo = Users(id , name , email)
                users.add(userinfo)
            }

        } catch (exception: SQLiteException){
            Log.d("exception" , "getUser: ${exception.message}")
        }
        return users


    }
    fun getUser(number: String): Users {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = '$number'"

        try {
            val cursor = db.rawQuery(query, null)
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                //val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                 return  Users(id , name , "email")

            }

        } catch (exception: SQLiteException){
            Log.d("exception" , "getUser: ${exception.message}")
        }

        return Users(" ", " ", " ")


    }

    fun changeUser(newName: String, oldName: String){
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME , newName)
        db.update(TABLE_NAME , values , "$COLUMN_NAME = '$oldName' " ,null)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"

        db.execSQL(dropTable)
        onCreate(db)
    }

    fun delete(name:String){
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_NAME = '$name' ",  null)
    }




}