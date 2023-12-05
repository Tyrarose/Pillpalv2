package com.codingwithme.meowbottomnavigationbar


import DatabaseHelper
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase

class DDI_dataDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase
    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }

    fun insertWord(drug1_id: String, drug2_id: String, drug1_name: String, drug2_name: String, interaction_type: String): Long {
        val values = ContentValues()
//        values.put(dbHelper.DRUG1_NAME, drug1_name)
//        values.put(dbHelper.DRUG2_NAME, drug2_name)
//        values.put(dbHelper.INTERACTION_TYPE, interaction_type)
//        return database.insert(DDIdata_databasehelper.DB_NAME, null, values)
        return 0
    }
    /*
    fun getMeaning(word: String): String? {
        val query = "SELECT ${DDIdata_databasehelper.INTERACTION_TYPE} FROM ${DDIdata_databasehelper.TBL_DDIDATA} WHERE ${DDIdata_databasehelper.DRUG1_NAME}"
        val cursor = database.rawQuery(query, arrayOf(word))
        return try {
            if (cursor != null && cursor.moveToFirst()) {
                cursor.getString(cursor.getColumnIndex(DDIdata_databasehelper.INTERACTION_TYPE))
            } else {
                null
            }
        } catch (e: SQLException) {
            null
        } finally {
            cursor.close()
        }
    }
    */
}
