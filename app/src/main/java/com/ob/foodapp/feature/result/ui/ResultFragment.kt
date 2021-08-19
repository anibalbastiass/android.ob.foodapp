package com.ob.foodapp.feature.result.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.ob.foodapp.BuildConfig
import com.ob.foodapp.FoodAppNavigator
import com.ob.foodapp.R
import com.ob.foodapp.databinding.FragmentResultBinding
import com.ob.foodapp.feature.result.presentation.viewmodel.ResultViewModel
import com.ob.foodapp.feature.result.presentation.viewstate.ResultViewState
import com.ob.foodapp.feature.result.ui.pager.ResultPagerAdapter
import com.ob.mvicore.observe
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class ResultFragment : Fragment(R.layout.fragment_result), KodeinAware {

    private var mainView: View? = null

    @SuppressWarnings("LeakingThisInConstructor")
    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein by kodein()
    override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    private lateinit var binding: FragmentResultBinding
    private val resultViewModel: ResultViewModel by instance()
    private val navigator: FoodAppNavigator by instance()
    private val resultAdapter = ResultAdapter()

    private val stateObserver = Observer<ResultViewState> { viewState ->
        when (viewState) {
            ResultViewState.InitialState -> {
                // Nothing to do here
            }
            is ResultViewState.GetItems -> {
                resultAdapter.items = viewState.list
                resultAdapter.notifyDataSetChanged()
            }
            ResultViewState.NotFoundItems -> {
                Toast.makeText(requireContext(), "No Items!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        kodeinTrigger?.trigger()

        this.mainView = view

        initTitle()
        initResultAdapter()
        onSelectTabItem()

        observe(resultViewModel.stateLiveData, stateObserver)

        resultViewModel.getItemsByCategory(binding.tlCategories.getTabAt(0)?.text.toString())
    }

    private fun onSelectTabItem() {
        binding.tlCategories.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                MainScope().launch {
                    delay(500)
                    resultViewModel.getItemsByCategory(tab.text.toString())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
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

        binding.ivProfile.setOnClickListener {
            navigator.navigateToProfile(mainView!!, "")
        }
    }

}