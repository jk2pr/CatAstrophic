package com.jk.catastrophic.di

import com.jk.catastrophic.ui.fragments.CatFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeCatFragment(): CatFragment
}