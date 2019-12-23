package com.jk.catastrophic.utils

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jk.catastrophic.data.Cat
import com.jk.catastrophic.service.ICatApi

class CatDataSourceFactory(private val api: ICatApi) : DataSource.Factory<Int, Cat>() {

      val catRepoLiveDataSource = MutableLiveData<CatDataSource>()

    override fun create(): DataSource<Int, Cat>{

        val catDataSource = CatDataSource(api)
        catRepoLiveDataSource.postValue(catDataSource)
        return catDataSource

    }

}
