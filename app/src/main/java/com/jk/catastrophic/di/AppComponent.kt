package com.jk.catastrophic.di

import android.app.Application
import com.jk.catastrophic.CatAstrophicApplication
import com.jk.catastrophic.ui.adapters.CatRecyclerViewAdapter
import com.jk.catastrophic.viewmodels.CatFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class, AppModule::class, ActivityBuilderModule::class])
interface AppComponent : AndroidInjector<CatAstrophicApplication> {

    fun inject(auth: CatFragmentViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CatAstrophicApplication): Builder

        fun build(): AppComponent
    }

}