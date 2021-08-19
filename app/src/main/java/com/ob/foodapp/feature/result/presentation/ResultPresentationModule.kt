package com.ob.foodapp.feature.result.presentation

import androidx.fragment.app.Fragment
import com.ob.foodapp.feature.result.MODULE_NAME
import com.ob.foodapp.feature.result.presentation.mapper.UiResultMapper
import com.ob.foodapp.feature.result.presentation.viewmodel.ResultViewModel
import com.ob.mvicore.viewmodel.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<ResultViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) {
            ResultViewModel(
                instance(),
                instance()
            )
        }
    }

    bind() from singleton { UiResultMapper() }
}
