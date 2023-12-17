package com.example.myapplication9.app

import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val dbHelper = MyDbHelper(context!!)
        val db = dbHelper.writableDatabase

        when (intent?.action) {
            Intent.ACTION_SCREEN_ON, Intent.ACTION_SCREEN_OFF -> {
                val actionState = if (intent.action == Intent.ACTION_SCREEN_ON) "Screen On" else "Screen Off"
                saveAction(db, actionState)
            }

            Intent.ACTION_POWER_CONNECTED, Intent.ACTION_POWER_DISCONNECTED -> {
                val actionState =
                    if (intent.action == Intent.ACTION_POWER_CONNECTED) "Charger Connected" else "Charger Disconnected"
                saveAction(db, actionState)
            }
        }

        db.close()
    }

    private fun saveAction(db: SQLiteDatabase, actionState: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentTime = dateFormat.format(Date())

        val values = ContentValues().apply {
            put(MyDbHelper.ActionsEntry.COLUMN_NAME_ACTION_TIME, currentTime)
            put(MyDbHelper.ActionsEntry.COLUMN_NAME_ACTION_STATE, actionState)
        }

        val newRowId = db?.insert(MyDbHelper.ActionsEntry.TABLE_NAME, null, values)
        Log.d("MyBroadcastReceiver", "Action saved. Row ID: $newRowId")
    }
}
