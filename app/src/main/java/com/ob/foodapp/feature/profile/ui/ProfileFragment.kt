package com.ob.foodapp.feature.profile.ui

import androidx.fragment.app.Fragment
import com.ob.foodapp.BuildConfig
import com.ob.foodapp.R
import com.ob.foodapp.databinding.FragmentProfileBinding
import com.ob.foodapp.databinding.FragmentSignInBinding
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.kcontext

class ProfileFragment: Fragment(R.layout.fragment_profile), KodeinAware {

    @SuppressWarnings("LeakingThisInConstructor")
    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein by kodein()
    override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    private lateinit var binding: FragmentProfileBinding
}