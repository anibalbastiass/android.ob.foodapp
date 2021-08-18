package com.ob.mvicore

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.ref.WeakReference

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

private var lastLifecycleOwner: WeakReference<LifecycleOwner>? = null

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: Observer<T>) {
    lastLifecycleOwner?.get()?.let {
        liveData.removeObservers(it)
    }
    lastLifecycleOwner = WeakReference(this)
    liveData.observe(this, observer)
}
