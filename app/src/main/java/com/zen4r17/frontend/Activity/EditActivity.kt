package com.zen4r17.frontend.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.zen4r17.frontend.Model.NoteModel
import com.zen4r17.frontend.Model.SubmitModel
import com.zen4r17.frontend.Service.APIRetrofit
import com.zen4r17.simpleappnote.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {

    private lateinit var etNote: EditText
    private lateinit var btnUpdate: MaterialButton
    private val api by lazy { APIRetrofit().endPoint }
    private val note by lazy { intent.getSerializableExtra("note") as NoteModel.Data }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        supportActionBar!!.title = "Edit Note"

        // setUpListener()
        setUpView()
    }


    private fun setUpView() {
        etNote = findViewById(R.id.et_note)
        btnUpdate = findViewById(R.id.btn_update)

        etNote.setText(note.note)

        btnUpdate.setOnClickListener {

            api.update(note.id!!, etNote.text.toString())
                .enqueue(object : Callback<SubmitModel> {
                    override fun onResponse(
                        call: Call<SubmitModel>,
                        response: Response<SubmitModel>
                    ) {

                        if (response.isSuccessful) {

                            val submitResult = response.body()
                            Toast.makeText(
                                applicationContext,
                                "Update success",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            finish()

                        }

                    }

                    override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                        TODO("Not yet implemented")
                    }


                })

        }

    }

}