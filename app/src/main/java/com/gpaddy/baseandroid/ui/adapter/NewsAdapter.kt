package com.gpaddy.baseandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gpaddy.baseandroid.data.model.api.Item
import com.gpaddy.baseandroid.databinding.ItemNews2Binding
import com.gpaddy.baseandroid.databinding.ItemNewsBinding
import com.gpaddy.baseandroid.ui.fragment.HomeFragmentDirections

class NewsAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(BaseDiffCallBack()) {


    override fun getItemViewType(position: Int): Int {
        return position % 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        if (viewType == 0) {
            return MyViewHolder(
                ItemNews2Binding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), viewType
            )
        }
       return MyViewHolder(
           ItemNewsBinding.inflate(
               LayoutInflater.from(parent.context), parent, false
           ),
           viewType
       )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as MyViewHolder).bind(data)

    }

    inner class MyViewHolder(
        val binding: ViewDataBinding,
        val  viewType: Int
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
//
           if (viewType==0){
                binding as ItemNews2Binding
                binding.setOnClickItem {view->
                    binding.data?.let {
                        openFragment(view.findNavController(), it)
                    }
                }
            }else{
                binding as ItemNewsBinding
               binding.setOnClickItem {view->
                   binding.data?.let {
                       openFragment(view.findNavController(), it)
                   }
               }
            }

        }
        fun openFragment(
            findNavController: NavController,
            it: Item
        ) {
            val dir= HomeFragmentDirections.actionHomeFragmentToNewsFragment(it)
            findNavController.navigate(dir)
        }
        fun bind(data1: Item) {
            if (viewType==0){
                binding as ItemNews2Binding
                binding.apply {
                    data = data1
                    executePendingBindings()
                }
            }else{
                binding as ItemNewsBinding
                binding.apply {
                    data = data1
                    executePendingBindings()
                }
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