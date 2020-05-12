package com.gzeinnumer.trainingdaggerpandeckt

import android.content.Context

//todo 23
class SessionManager (private val context: Context) {

    private val TAG = "SessionManager"

    companion object {
        private const val PREF_NAME = "session"
        private const val KEY_ID = "id"
    }

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val editor = prefs.edit()

    fun setId(id: Int) {
        editor.putInt(KEY_ID, id).apply()
    }

    fun getId(): Int = prefs.getInt(KEY_ID, 0)
}