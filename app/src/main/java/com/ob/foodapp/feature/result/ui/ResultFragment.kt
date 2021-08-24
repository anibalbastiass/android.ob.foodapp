package com.ob.foodapp.feature.result.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.ob.foodapp.BuildConfig
import com.ob.foodapp.FoodAppNavigator
import com.ob.foodapp.R
import com.ob.foodapp.databinding.FragmentResultBinding
import com.ob.foodapp.feature.profile.presentation.viewmodel.ProfileViewModel
import com.ob.foodapp.feature.profile.presentation.viewstate.ProfileViewState
import com.ob.foodapp.feature.result.presentation.viewmodel.ResultViewModel
import com.ob.foodapp.feature.result.presentation.viewstate.ResultViewState
import com.ob.foodapp.feature.result.ui.pager.ResultPagerAdapter
import com.ob.mvicore.observe
import com.ob.uicore.utils.ImageUtils
import com.ob.uicore.utils.ImageUtils.loadImage
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
    private val args: ResultFragmentArgs by navArgs()
    private val resultViewModel: ResultViewModel by instance()
    private val profileViewModel: ProfileViewModel by instance()
    private val navigator: FoodAppNavigator by instance()
    private val resultAdapter = ResultAdapter()

    private val stateObserver = Observer<ResultViewState> { viewState ->
        when (viewState) {
            ResultViewState.InitialState -> {
                // Nothing to do here
            }
            is ResultViewState.GetItems -> {
                resultAdapter.setData(viewState.list)
            }
            ResultViewState.NotFoundItems -> {
                Toast.makeText(requireContext(), "No Items!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val profileStateObserver = Observer<ProfileViewState> { viewState ->
        when (viewState) {
            is ProfileViewState.GetProfile -> {
                resultViewModel.setUserId(args.user.uid)
                resultViewModel.setLikes(viewState.profile.likes)
                resultViewModel.getItemsByCategory(
                    category = binding.tlCategories.getTabAt(0)?.text.toString()
                )

                // Update avatar Url in icon
                with(ImageUtils) {
                    binding.ivProfile.loadImage(
                        imageUrl = viewState.profile.avatarUrl,
                        placeHolder = R.drawable.profile_placeholder,
                        isRounded = true
                    )
                }
            }
            else -> {
                resultViewModel.setUserId(args.user.uid)
                resultViewModel.getItemsByCategory(
                    category = binding.tlCategories.getTabAt(0)?.text.toString()
                )
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
        observe(profileViewModel.stateLiveData, profileStateObserver)
        fetchProfileData()
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
            navigator.navigateToProfile(mainView!!, args.user.uid)
        }
    }

    private fun fetchProfileData() {
        profileViewModel.getProfile(args.user.uid)
    }
}