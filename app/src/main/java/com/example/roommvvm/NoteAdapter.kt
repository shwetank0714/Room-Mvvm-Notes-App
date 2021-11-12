package com.example.roommvvm

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val listener: NoteRVAdapter):RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    val allNotes = ArrayList<NoteEntity>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.noteTv)!!
        val delete = itemView.findViewById<ImageView>(R.id.delTv)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))

        viewHolder.delete.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }

        return  viewHolder
    }


    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = allNotes[position].text
    }

    fun updateList(newList: List<NoteEntity>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface NoteRVAdapter {
    fun onItemClicked(noteEntity: NoteEntity)
}