package ru.cft.shift2022winter.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2022winter.R
import ru.cft.shift2022winter.domain.model.database.ValuteModel

class ValutesAdapter(private val onItemClick: (ValuteModel) -> Unit) : RecyclerView.Adapter<CharacterHolder>() {

    var valutes: List<ValuteModel> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_valute, parent, false)
        return CharacterHolder(onItemClick, view)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val current = valutes[position]
        holder.bind(current)
    }

    override fun getItemCount(): Int = valutes.size
}

class CharacterHolder(private val onItemClick: (ValuteModel) -> Unit, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameText: TextView = itemView.findViewById(R.id.nameText)
    private val nicknameText: TextView = itemView.findViewById(R.id.nicknameText)

    fun bind(valute: ValuteModel) {
        nameText.text = valute.name
        nicknameText.text = valute.value.toString()
        itemView.setOnClickListener { onItemClick(valute) }
    }
}