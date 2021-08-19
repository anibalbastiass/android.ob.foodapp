package com.ob.foodapp.feature.result.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ob.foodapp.BuildConfig
import com.ob.foodapp.R
import com.ob.foodapp.databinding.FragmentResultBinding
import com.ob.foodapp.feature.result.ui.pager.ResultPagerAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.kcontext

class ResultFragment : Fragment(R.layout.fragment_result), KodeinAware {

    @SuppressWarnings("LeakingThisInConstructor")
    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein by kodein()
    override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    private lateinit var binding: FragmentResultBinding

    private val resultAdapter = ResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        kodeinTrigger?.trigger()
        initTitle()
        initResultAdapter()
//        onSelectTabItem()
    }

    private fun initResultAdapter() {
        binding.vpResult.adapter = ResultPagerAdapter(fragmentManager)
        binding.tlCategories.setupWithViewPager(binding.vpResult)
        binding.rvResult.setResultAdapter()
    }

    private fun RecyclerView.setResultAdapter() {
        layoutManager = GridLayoutManager(context, 1)
        adapter = resultAdapter
    }

    private fun initTitle() {
        binding.tvResultTitle.initTitleText(
            context = requireContext(),
            fullText = getString(R.string.what_would_),
            boldText = "you like?"
        )
    }

}