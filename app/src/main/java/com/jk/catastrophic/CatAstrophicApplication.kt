package com.jk.catastrophic

import com.jk.catastrophic.di.AppComponent
import com.jk.catastrophic.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class CatAstrophicApplication : DaggerApplication() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()



    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this);
        return appComponent
    }


}