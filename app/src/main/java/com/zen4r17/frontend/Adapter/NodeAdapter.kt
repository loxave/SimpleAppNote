package com.zen4r17.frontend.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zen4r17.frontend.Model.NoteModel
import com.zen4r17.simpleappnote.R

class NodeAdapter(
    val notes: ArrayList<NoteModel.Data>,
    val listener: onAdapterListener,

    ) : RecyclerView.Adapter<NodeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_note, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = notes[position]
        holder.tvNote.text = data.note
        holder.itemView.setOnClickListener {

            listener.onUpdate(data)

            holder.imgDelete.setOnClickListener {

                listener.onDelete(data)

            }
        }


    }


    override fun getItemCount() = notes.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNote = view.findViewById<TextView>(R.id.tv_note)
        val imgDelete = view.findViewById<ImageView>(R.id.img_delete)


    }


    public fun setData(data: List<NoteModel.Data>) {

        notes.clear()
        notes.addAll(data)
        notifyDataSetChanged()

    }

    interface onAdapterListener {

        fun onUpdate(note: NoteModel.Data)
        fun onDelete(note: NoteModel.Data)
    }
}