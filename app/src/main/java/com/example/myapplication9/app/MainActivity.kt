package com.example.myapplication9.app

import ActionAdapter
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication9.R

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: MyDbHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ActionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = MyDbHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ActionAdapter(getAllActions())
        recyclerView.adapter = adapter
    }

    private fun getAllActions(): List<Action> {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            MyDbHelper.ActionsEntry.COLUMN_NAME_ACTION_TIME,
            MyDbHelper.ActionsEntry.COLUMN_NAME_ACTION_STATE
        )

        val cursor: Cursor = db.query(
            MyDbHelper.ActionsEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val actions = mutableListOf<Action>()

        with(cursor) {
            while (moveToNext()) {
                val time = getString(getColumnIndexOrThrow(MyDbHelper.ActionsEntry.COLUMN_NAME_ACTION_TIME))
                val state = getString(getColumnIndexOrThrow(MyDbHelper.ActionsEntry.COLUMN_NAME_ACTION_STATE))
                actions.add(Action(time, state))
            }
        }

        cursor.close()
        db.close()

        return actions
    }
}
