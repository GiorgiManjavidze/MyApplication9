package com.example.myapplication9.app

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns


class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    companion object {
        const val DATABASE_NAME = "actionLog.db"
        const val DATABASE_VERSION = 1

        object ActionsEntry : BaseColumns {
            const val TABLE_NAME = "actions"
            const val COLUMN_NAME_ACTION_TIME = "action_time"
            const val COLUMN_NAME_ACTION_STATE = "action_state"
        }
    }



    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${ActionsEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${ActionsEntry.COLUMN_NAME_ACTION_TIME} TEXT," +
                "${ActionsEntry.COLUMN_NAME_ACTION_STATE} TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ActionsEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}
