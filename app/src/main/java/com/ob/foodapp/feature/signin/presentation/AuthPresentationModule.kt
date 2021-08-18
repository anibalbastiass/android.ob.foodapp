package com.ob.foodapp.feature.signin.presentation

import androidx.fragment.app.Fragment
import com.ob.foodapp.feature.signin.MODULE_NAME
import com.ob.foodapp.feature.signin.presentation.mapper.UiUserMapper
import com.ob.foodapp.feature.signin.presentation.viewmodel.AuthViewModel
import com.ob.mvicore.viewmodel.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<AuthViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) {
            AuthViewModel(
                instance(),
                instance()
            )
        }
    }

    bind() from singleton { UiUserMapper() }
}
