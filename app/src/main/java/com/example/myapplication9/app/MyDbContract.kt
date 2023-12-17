package com.example.myapplication9.app

import android.provider.BaseColumns

class MyDbContract {
    // ...

    companion object {
        object ActionsEntry : BaseColumns {
            const val TABLE_NAME = "actions"
            const val COLUMN_NAME_ACTION_TIME = "action_time"
            const val COLUMN_NAME_ACTION_STATE = "action_state"
        }
    }
}
