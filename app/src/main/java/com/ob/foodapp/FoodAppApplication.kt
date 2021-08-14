package com.ob.foodapp

import android.app.Application
import com.ob.foodapp.di.FragmentArgsExternalSource
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import timber.log.Timber

class FoodAppApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FoodAppApplication))
        externalSources.add(FragmentArgsExternalSource())

        bind() from singleton { FoodAppNavigator() }
        // TODO: Add feature modules
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
