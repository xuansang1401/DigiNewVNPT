package com.gpaddy.baseandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.data.model.api.NewsModel

import com.gpaddy.baseandroid.databinding.ItemDemoBinding
import com.gpaddy.baseandroid.util.RxBus

class NewsAdapter : ListAdapter<NewsModel, RecyclerView.ViewHolder>(BaseDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemDemoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as MyViewHolder).bind(data)
    }

    inner class MyViewHolder(val binding: ItemDemoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setOnClickItem {
                binding.data?.let { data ->
                    RxBus.publish(data)
                    it.findNavController().navigate(R.id.detailNetworkFragment)
                }
            }
        }
        fun bind(data1: NewsModel) {
            binding.apply {
                data = data1
                executePendingBindings()
            }
        }

    }

    private class BaseDiffCallBack : DiffUtil.ItemCallback<NewsModel>() {

        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.i == newItem.i
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }
    }


}