package com.example.followchess.models

import androidx.recyclerview.widget.DiffUtil

data class Trn(
    val img: String,
    val name: String,
    val slug: String,
    val status: Int
)

object DiffTrn: DiffUtil.ItemCallback<Trn>() {
    override fun areItemsTheSame(oldItem: Trn, newItem: Trn): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Trn, newItem: Trn): Boolean {
        return oldItem.name==newItem.name
    }
}