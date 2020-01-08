package com.jk.catastrophic.di

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jk.catastrophic.CatAstrophicApplication
import com.jk.catastrophic.R
import dagger.Module
import dagger.Provides

@Module
class AppModule {


        @Provides
        fun provideGlideRequestOptions(): RequestOptions {
            return RequestOptions()
                .error(R.drawable.ic_terrain_black_24dp)

        }

        @Provides
        fun provideGlide(
            application: CatAstrophicApplication,
            requestOptions: RequestOptions
        ): RequestManager {
            return Glide.with(application).setDefaultRequestOptions(requestOptions)

        }



}