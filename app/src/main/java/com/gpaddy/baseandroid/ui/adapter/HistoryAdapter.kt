//package com.gpaddy.baseandroid.ui.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.orhanobut.logger.Logger
//
//import com.gpaddy.baseandroid.data.model.db.HistoryModel
//import com.gpaddy.baseandroid.data.room.DatabaseDao
//import com.gpaddy.baseandroid.databinding.ItemHistoryBinding
//
//class HistoryAdapter  constructor(
//    private val databaseDao: DatabaseDao
//) : ListAdapter<HistoryModel, RecyclerView.ViewHolder>(BaseDiffCallBack()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
//        return RecommendViewHolder(
//            ItemHistoryBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val data = getItem(position)
//        (holder as RecommendViewHolder).bind(data)
//    }
//
//    inner class RecommendViewHolder(val binding: ItemHistoryBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        init {
//            binding.btnXoa.setOnClickListener {
//                binding.data?.let { his ->
//                    Logger.e("error $his");
//
////                    Log.e("sang", "xoa: $his")
//                    databaseDao.deleteHis(his)
//                }
//            }
//        }
//
//        fun bind(data1: HistoryModel) {
//            binding.apply {
//                data = data1
//                executePendingBindings()
//            }
//        }
//
//    }
//
//    private class BaseDiffCallBack : DiffUtil.ItemCallback<HistoryModel>() {
//
//        override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
//            return oldItem.id_his == newItem.id_his
//        }
//
//        override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//}