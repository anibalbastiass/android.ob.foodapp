package com.ob.foodapp.feature.result.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ob.foodapp.BuildConfig
import com.ob.foodapp.R
import com.ob.foodapp.databinding.FragmentResultBinding
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.kcontext

class ResultFragment: Fragment(R.layout.fragment_result), KodeinAware {

    @SuppressWarnings("LeakingThisInConstructor")
    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein by kodein()
    override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    private lateinit var binding: FragmentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        kodeinTrigger?.trigger()
    }

}