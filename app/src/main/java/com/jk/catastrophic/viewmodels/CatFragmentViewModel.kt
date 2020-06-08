package com.jk.catastrophic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jk.catastrophic.CatAstrophicApplication
import com.jk.catastrophic.data.Cat
import com.jk.catastrophic.service.ICatApi
import com.jk.catastrophic.utils.CatDataSource
import com.jk.catastrophic.utils.CatDataSourceFactory

class CatFragmentViewModel(api: ICatApi) :
    ViewModel() {

    var catPagedList: LiveData<PagedList<Cat>>

    private var liveDataSource: LiveData<CatDataSource>

    init {

        val itemDataSourceFactory = CatDataSourceFactory(api)

        liveDataSource = itemDataSourceFactory.catRepoLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        catPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()

        /* catLiveData.value = CatResource.loading(data = null)
         GlobalScope.launch(Dispatchers.Main) {
             val postRequest = api.getCatsAsync("20", "1", "png")
             try {
                 val response = postRequest.await()
                 if (response.isSuccessful) {
                     val posts = response.body()
                     catLiveData.value = CatResource.authenticated(posts)
                 } else {
                     catLiveData.value = CatResource.error(response.errorBody().toString(), null)
                     Log.d("MainActivity ", response.errorBody().toString())
                 }

             } catch (e: Exception) {

             }
         }*/
    }
}

@Suppress("UNCHECKED_CAST")
class MyViewModelFactory(
    private val param: ICatApi,
    application: CatAstrophicApplication
) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatFragmentViewModel(param) as T
    }
}