package com.gzeinnumer.trainingdaggerpandeckt

import android.content.Context
import android.widget.Toast
import com.gzeinnumer.trainingdaggerpandeckt.util.myLogD
import javax.inject.Inject
import javax.inject.Singleton

//todo 27
@Singleton
class RepositoryExample @Inject constructor(
    private val context: Context,
    private val sessionManager: SessionManager
) {
    val TAG = "RepositoryExample"

    fun exampleFunction() {
        Toast.makeText(context, "exampleFunction", Toast.LENGTH_SHORT).show()
        myLogD(TAG, "exampleFunction : " + sessionManager.getId())
    }

}