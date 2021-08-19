package com.ob.foodapp.feature.profile.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ob.foodapp.BuildConfig
import com.ob.foodapp.R
import com.ob.foodapp.databinding.FragmentProfileBinding
import com.ob.foodapp.databinding.FragmentSignInBinding
import com.ob.foodapp.feature.profile.presentation.viewmodel.ProfileViewModel
import com.ob.foodapp.feature.profile.presentation.viewstate.ProfileViewState
import com.ob.foodapp.feature.signin.presentation.viewmodel.AuthViewModel
import com.ob.foodapp.feature.signin.presentation.viewstate.SignInViewState
import com.ob.mvicore.observe
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class ProfileFragment : Fragment(R.layout.fragment_profile), KodeinAware {

    @SuppressWarnings("LeakingThisInConstructor")
    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein by kodein()
    override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by instance()

    private val stateObserver = Observer<ProfileViewState> { viewState ->
        when (viewState) {
            ProfileViewState.InitialState -> {
                // Nothing to do here
            }
            is ProfileViewState.GetProfile -> {
                val profile = viewState.profile

                binding.etName.setText(profile.name)
                binding.etCity.setText(profile.city)
                binding.etBio.setText(profile.bio)

            }
            ProfileViewState.NotFoundProfile -> {
                Toast.makeText(requireContext(), "User Not Found!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        kodeinTrigger?.trigger()

        observe(profileViewModel.stateLiveData, stateObserver)
        fetchProfileData()
    }

    private fun fetchProfileData() {
        profileViewModel.getProfile("ivan.simmons@example.com")
    }
}