package com.gpaddy.baseandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gpaddy.baseandroid.base.ui.DataBindingFragment
import com.gpaddy.baseandroid.data.model.api.Data
import com.gpaddy.baseandroid.data.model.api.Item
import com.gpaddy.baseandroid.databinding.ItemNews2Binding
import com.gpaddy.baseandroid.databinding.ItemNewsBinding
import com.gpaddy.baseandroid.databinding.ItemVideoBinding
import com.gpaddy.baseandroid.ui.fragment.CategoryDetailFragmentDirections
import com.gpaddy.baseandroid.ui.fragment.CategoryFragmentDirections
import com.gpaddy.baseandroid.ui.fragment.HomeFragmentDirections
import com.gpaddy.baseandroid.util.RxBus

class VideoAdapter : ListAdapter<Data, RecyclerView.ViewHolder>(BaseDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as MyViewHolder).bind(data)

    }

    inner class MyViewHolder(
        val binding: ItemVideoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setOnClickItem {view->
                binding.data?.let {
//                    val dir= CategoryFragmentDirections.actionCategoryFragmentToNewsFragment(it)
//                    view.findNavController().navigate(dir)
                }
            }
        }

        fun bind(data1: Data) {

            binding.apply {
                data = data1
                executePendingBindings()
            }


        }

    }

    private class BaseDiffCallBack : DiffUtil.ItemCallback<Data>() {

        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }


}