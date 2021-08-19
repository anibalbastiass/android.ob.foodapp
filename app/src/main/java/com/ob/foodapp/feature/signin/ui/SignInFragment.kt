package com.ob.foodapp.feature.signin.ui

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ob.foodapp.BuildConfig
import com.ob.foodapp.FoodAppNavigator
import com.ob.foodapp.R
import com.ob.foodapp.databinding.FragmentSignInBinding
import com.ob.foodapp.feature.signin.presentation.viewmodel.AuthViewModel
import com.ob.foodapp.feature.signin.presentation.viewstate.SignInViewState
import com.ob.mvicore.observe
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class SignInFragment : Fragment(R.layout.fragment_sign_in), KodeinAware {

    @SuppressWarnings("LeakingThisInConstructor")
    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein by kodein()
    override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    private lateinit var binding: FragmentSignInBinding

    private val authViewModel: AuthViewModel by instance()
    private val navigator: FoodAppNavigator by instance()

    private var mainView: View? = null

    private val stateObserver = Observer<SignInViewState> { viewState ->
        when (viewState) {
            SignInViewState.InitialState -> {
                // Nothing to do here
            }
            is SignInViewState.UserFound -> {
                viewState.user.apply {
                    binding.pbSignIn.visibility = View.GONE
                    navigator.navigateToResult(mainView!!, email)
                }
            }
            SignInViewState.NotUserFound -> {
                binding.pbSignIn.visibility = View.GONE
                binding.btnSignIn.visibility = View.VISIBLE
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
        binding = FragmentSignInBinding.bind(view)
        kodeinTrigger?.trigger()

        this.mainView = view

        setupTitleView()
        setupSubtitleView()
        observeSignIn()
    }

    private fun observeSignIn() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            binding.pbSignIn.visibility = View.VISIBLE
            binding.btnSignIn.visibility = View.GONE
            authViewModel.signIn(email, password)
        }

        observe(authViewModel.stateLiveData, stateObserver)
    }

    private fun setupSubtitleView() {
        binding.signInTo.text = Html.fromHtml(getString(R.string.sign_in_to_))
        binding.signInTo.typeface =
            ResourcesCompat.getFont(requireContext(), R.font.poppins_regular)
    }

    private fun setupTitleView() {
        binding.firebaseAn.initTitleText(
            context = requireContext(),
            fullText = getString(R.string.firebase_an),
            boldText = "and\nfresh food"
        )
    }
}