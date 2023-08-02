package com.bongku.mwobwa

import android.app.Application
import com.bongku.mwobwa.data.datastore.MwobwaDataStore
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MwobwaApplication : Application() {
    @Inject
    lateinit var mwobwaDataStore: MwobwaDataStore

    companion object{
        lateinit var mwobwaApplication: MwobwaApplication
    }

    override fun onCreate() {
        super.onCreate()
        mwobwaApplication = this
    }
}