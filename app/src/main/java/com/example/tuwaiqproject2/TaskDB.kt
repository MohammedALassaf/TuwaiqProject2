package com.example.tuwaiqproject2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class TaskDB(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        // DB info
        const val DATABASE_NAME = "task_DB"
        const val DATABASE_VERSION = 1

        //Table info
        const val TABLE_NAME = "task_table"
        const val COLUMN_NUMBER = "number"
        const val COLUMN_TASK = "task"
        const val COLUMN_COMPLETE = "complete"


    }

    fun insertTask(tasks: Tasks) {
//        writableDatabase
//        readableDatabase
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NUMBER, tasks.number)
        values.put(COLUMN_TASK, tasks.task)
        values.put(COLUMN_COMPLETE, tasks.complete)

        db.insert(TABLE_NAME, null, values)
        // NULL COLUMN HACK  -> this is when i don't insert value (i.e we didn't insert ID) it will
        //check if it's a primary key and auto increment otherwise leaves it as NULL
    }

    override fun onCreate(db: SQLiteDatabase) {
        val initTable = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_TASK TEXT PRIMARY KEY, $COLUMN_COMPLETE TEXT, $COLUMN_NUMBER TEXT)"

        db.execSQL(initTable)
    }

    fun getTasks(): List<Tasks> {
        val tasks = arrayListOf<Tasks>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"

        try {
            val cursor = db.rawQuery(query, null)
            cursor.moveToFirst()
            if (cursor.isFirst) {
                do {
                    val number = cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER))
                    val task = cursor.getString(cursor.getColumnIndex(COLUMN_TASK))
                    val complete = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETE))
                    val taskinfo = Tasks(number, task, complete)
                    tasks.add(taskinfo)
                } while (cursor.moveToNext())
            }
        } catch (exception: SQLiteException) {
            Log.d("exception", "getUser: ${exception.message}")
        }
        return tasks


    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(dropTable)
        onCreate(db)
    }


}