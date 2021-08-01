package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.ui.adapter.NewsAdapter
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.data.model.api.ExploreModel
import com.gpaddy.baseandroid.databinding.ExploreFragmentBinding
import com.gpaddy.baseandroid.databinding.MovieFragmentBinding
import com.gpaddy.baseandroid.viewmodel.HomeViewModel
import com.gpaddy.baseandroid.viewmodel.NetworkViewModel

class ExploreDetailFragment : BaseFragment(){
    private lateinit var mState: HomeViewModel

    private val args: ExploreDetailFragmentArgs by navArgs()
    override fun initViewModel() {
        mState=getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.movie_fragment,
            BR.vm,
            mState
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = mBinding as MovieFragmentBinding
        val data= getDataById(args.id)
        binding.image.setImageResource(data.idImage)
        binding.textView1.text=getString(data.idNote)
        binding.textView6.text=getString(data.idTitle)
        binding.btnBack.setOnClickListener {
            nav().navigateUp()
        }
    }


    fun getDataById(id: Int): ExploreModel{
        when(id){
            0-> return ExploreModel(R.string.digimovie, R.drawable.app_movie, R.string.note_movie)
            1-> return ExploreModel(R.string.digiclip, R.drawable.app_clip, R.string.note_clip)
            2-> return ExploreModel(R.string.digimusic, R.drawable.app_music, R.string.note_music)
            3-> return ExploreModel(R.string.digihealth, R.drawable.app_health, R.string.note_health)
            4-> return ExploreModel(R.string.mytv, R.drawable.mytv, R.string.note_mytv)
        }
        return ExploreModel(R.string.digimovie, R.drawable.app_movie, R.string.note_movie)
    }
}