package com.gzeinnumer.trainingdaggerpandeckt

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.gzeinnumer.trainingdaggerpandeckt.network.ApiService
import com.gzeinnumer.trainingdaggerpandeckt.network.ResponsePost
import com.gzeinnumer.trainingdaggerpandeckt.util.myLogD
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//todo 9 ubah AppCompatActivity ke DaggerAppCompatActivity
class MainActivity : DaggerAppCompatActivity() {

    private val TAG = "MainActivity"

    //todo 13
    @Inject
    lateinit var str: String

    //todo 16
    @Inject
    lateinit var context: Context

    //todo 21
    @Inject
    lateinit var apiService: ApiService

    //todo 25
    @Inject
    lateinit var sessionManager: SessionManager

    //todo 28
    @Inject
    lateinit var repositoryExample: RepositoryExample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo 14
        myLogD(TAG, "String Inject : $str")

        //todo 17
        Toast.makeText(context, "String Inject : $str", Toast.LENGTH_LONG).show()

        //todo 22
        apiService.getPost(1)
            .enqueue(object : Callback<List<ResponsePost>> {
                override fun onResponse(call: Call<List<ResponsePost>>, response: Response<List<ResponsePost>>) {
                    myLogD(TAG, "ApiService Inject(Success) : " + response.body()!!.size)
                }

                override fun onFailure(call: Call<List<ResponsePost>>, t: Throwable) {
                    myLogD(TAG, "ApiService Inject(error) : " + t.message)
                }
            })

        //todo 26
//        sessionManager = SessionManager(applicationContext);
        sessionManager.setId(1)
        Toast.makeText(context, sessionManager.getId().toString(), Toast.LENGTH_SHORT).show()
        myLogD(TAG, "Session Inject : " + sessionManager.getId())

        //todo 29
//        val mySession = SessionManager(applicationContext);
//        repositoryExample = RepositoryExample(applicationContext, mySession);
        repositoryExample.exampleFunction()
    }
}
