package com.ziad.marvelmdi

import android.app.Application
import com.airbnb.epoxy.Carousel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Carousel.setDefaultGlobalSnapHelperFactory(null)
    }
}