package com.zen4r17.frontend.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.zen4r17.frontend.Model.SubmitModel
import com.zen4r17.frontend.Service.APIRetrofit
import com.zen4r17.simpleappnote.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivity : AppCompatActivity() {

    private lateinit var etNote: EditText
    private lateinit var btnCreate: MaterialButton
    private val api by lazy { APIRetrofit().endPoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        supportActionBar!!.title = "New Note"

        // setUpListener()
        setUpView()
    }

//    private fun setUpListener() {
//
//    }

    private fun setUpView() {
        etNote = findViewById(R.id.et_note)
        btnCreate = findViewById(R.id.btn_create)

        btnCreate.setOnClickListener {

            if (etNote.text.toString().isNotEmpty()) {

                Log.e("Create Activity", etNote.text.toString())
                api.create(etNote.text.toString())
                    .enqueue(object : Callback<SubmitModel> {
                        override fun onResponse(
                            call: Call<SubmitModel>,
                            response: Response<SubmitModel>
                        ) {

                            if (response.isSuccessful) {

                                val submitResult = response.body()
                                Toast.makeText(
                                    applicationContext,
                                    "Success Added",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                finish()

                            }

                        }

                        override fun onFailure(call: Call<SubmitModel>, t: Throwable) {

                        }


                    })
            } else {
                Toast.makeText(applicationContext, "Note cannot be blank", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
}