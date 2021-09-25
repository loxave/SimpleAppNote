package com.zen4r17.frontend.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zen4r17.frontend.Adapter.NodeAdapter
import com.zen4r17.frontend.Model.NoteModel
import com.zen4r17.frontend.Model.SubmitModel
import com.zen4r17.frontend.Service.APIRetrofit
import com.zen4r17.simpleappnote.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val api by lazy { APIRetrofit().endPoint }
    private lateinit var noteAdapter: NodeAdapter
    private lateinit var fabCr: FloatingActionButton
    private lateinit var listNote: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.title = "Note Here!"
        //  SetUpListener()
        SetUpView()
        setUpList()

    }

    private fun SetUpView() {
        listNote = findViewById(R.id.rv_list_node)
        fabCr = findViewById<FloatingActionButton>(R.id.fab_create)

        fabCr.setOnClickListener {

            startActivity(Intent(this, CreateActivity::class.java))
        }
    }


    private fun setUpList() {
        noteAdapter = NodeAdapter(arrayListOf(), object : NodeAdapter.onAdapterListener {
            override fun onUpdate(note: NoteModel.Data) {
                startActivity(
                    Intent(this@MainActivity, EditActivity::class.java)
                        .putExtra("note", note)
                )
            }

            override fun onDelete(note: NoteModel.Data) {
                api.delete(note.id!!)
                    .enqueue(object : Callback<SubmitModel> {
                        override fun onResponse(
                            call: Call<SubmitModel>,
                            response: Response<SubmitModel>
                        ) {
                            if (response.isSuccessful) {

                                val submitResult = response.body()
                                Toast.makeText(
                                    applicationContext,
                                    "Success Deleted",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                getNote()

                            }

                        }

                        override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                        }
                    })
            }

        })
        listNote = findViewById(R.id.rv_list_node)

        listNote.adapter = noteAdapter
    }

    private fun getNote() {

        api.data().enqueue(object : Callback<NoteModel> {
            override fun onResponse(call: Call<NoteModel>, response: Response<NoteModel>) {

                if (response.isSuccessful) {
                    val listData = response.body()!!.notes

                    noteAdapter.setData(listData)
//                    listData.forEach {
//                        Log.e("Main Activity", "Note: ${it.note}")
//                    }

                }

            }

            override fun onFailure(call: Call<NoteModel>, t: Throwable) {
                Log.e("Main Activity", t.toString())
            }

        })
    }

    override fun onStart() {
        super.onStart()
        getNote()
    }
}