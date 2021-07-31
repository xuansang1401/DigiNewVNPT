package com.gpaddy.baseandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gpaddy.baseandroid.base.ui.DataBindingFragment
import com.gpaddy.baseandroid.data.model.api.Item
import com.gpaddy.baseandroid.databinding.ItemNews2Binding
import com.gpaddy.baseandroid.databinding.ItemNewsBinding
import com.gpaddy.baseandroid.ui.fragment.CategoryDetailFragmentDirections
import com.gpaddy.baseandroid.ui.fragment.CategoryFragmentDirections
import com.gpaddy.baseandroid.ui.fragment.HomeFragmentDirections
import com.gpaddy.baseandroid.util.RxBus

class CategoryNewsAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(BaseDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as MyViewHolder).bind(data)

    }

    inner class MyViewHolder(
        val binding: ItemNewsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setOnClickItem {view->
                binding.data?.let {
                    val dir= CategoryFragmentDirections.actionCategoryFragmentToNewsFragment(it)
                    view.findNavController().navigate(dir)
                }
            }
        }

        fun bind(data1: Item) {

            binding.apply {
                data = data1
                executePendingBindings()
            }


        }

    }

    private class BaseDiffCallBack : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }


}