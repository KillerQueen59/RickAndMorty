package com.ojanbelajar.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojanbelajar.core.R
import com.ojanbelajar.core.databinding.ItemFavouriteBinding
import com.ojanbelajar.core.domain.model.Search
import java.util.ArrayList

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ListViewHolder>() {

    private var listData = ArrayList<Search>()
    var onItemClick: ((Search) -> Unit)? = null

    fun setData(newListData: List<Search>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favourite, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavouriteBinding.bind(itemView)
        fun bind(data: Search) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(imageFavourite)
                nameCharacter.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}