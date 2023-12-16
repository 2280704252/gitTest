package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var send = findViewById<Button>(R.id.sendRequesBtn)


        send.setOnClickListener {


            sendRequest()
        }


    }


    private fun sendRequest(){

        thread {
            try {

                val client=OkHttpClient()
                val request= Request.Builder()
                    .url("http://192.168.137.179:8080/api/words/search")
                    .get()
                    .build()

                var response = client.newCall(request).execute()
                var responseData = response.body?.string()
                if(responseData!=null){


                    showResponse(responseData)


                }



            }catch (e:Exception){

                e.printStackTrace()
            }



        }


    }

    private fun showResponse(response:String){
        var responseText = findViewById<TextView>(R.id.responseText)
        runOnUiThread{

            responseText.text=response

        }


    }

}