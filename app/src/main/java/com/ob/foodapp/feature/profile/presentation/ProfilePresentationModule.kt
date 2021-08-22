package com.ob.foodapp.feature.profile.presentation

import androidx.fragment.app.Fragment
import com.ob.foodapp.feature.profile.MODULE_NAME
import com.ob.foodapp.feature.profile.presentation.mapper.UiProfileMapper
import com.ob.foodapp.feature.profile.presentation.viewmodel.ProfileViewModel
import com.ob.mvicore.viewmodel.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<ProfileViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) {
            ProfileViewModel(
                instance(),
                instance(),
                instance()
            )
        }
    }

    bind() from singleton { UiProfileMapper() }
}
