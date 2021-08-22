package com.ob.foodapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.firebase.auth.FirebaseAuth
import com.ob.foodapp.databinding.ActivityFoodAppBinding
import com.ob.foodapp.feature.signin.presentation.model.UiUser
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.kodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.kcontext

class FoodAppActivity : AppCompatActivity(), KodeinAware {

    private val parentKodein by kodein()
    private lateinit var binding: ActivityFoodAppBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    @SuppressWarnings("LeakingThisInConstructor")
    override val kodeinContext = kcontext<AppCompatActivity>(this)

    private val navigator = FoodAppNavigator()

    override val kodein: Kodein by retainedKodein {
        extend(parentKodein)
    }

    override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        kodeinTrigger?.trigger()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityFoodAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Use the AuthViewModel instance for this
        checkSession()
    }

    private fun checkSession() {
        val navController = findNavController(R.id.navHostFragment)
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            navigator.launchAppWithSignIn(navController)
        } else {
            // TODO: Implement in AuthViewModel for avoid use this Builder
            navigator.launchAppWithResult(
                navController,
                UiUser(
                    uid = currentUser.uid,
                    name = currentUser.displayName ?: "",
                    email = currentUser.email ?: ""
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
