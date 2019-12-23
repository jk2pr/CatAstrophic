package com.jk.catastrophic.utils

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.jk.catastrophic.data.Cat
import com.jk.catastrophic.service.ICatApi
import kotlinx.coroutines.*

class CatDataSource(private val apiClient: ICatApi) : PageKeyedDataSource<Int, Cat>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Cat>
    ) {


        val job = GlobalScope.launch(Dispatchers.Main) {
            try {
                val postRequest =
                    apiClient.getCatsAsync(params.requestedLoadSize.toString(), FIRST_PAGE, "png")
                val response = postRequest.await()
                if (response.isSuccessful) {

                    val posts = response.body()

                    callback.onResult(posts!!.toMutableList(), 0, FIRST_PAGE+1)
                    //callback.value = CatResource.authenticated(posts)
                } else {
                    // catLiveData.value = CatResource.error(response.errorBody().toString(),null)
                    //  Log.d("MainActivity ", response.errorBody().toString())
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Cat>) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val postRequest =
                    apiClient.getCatsAsync(
                        params.requestedLoadSize.toString(),
                        params.key + 1,
                        "png"
                    )
                val response = postRequest.await()
                if (response.isSuccessful) {
                    val items = response.body()
                    callback.onResult(items!!.toMutableList(), params.key+1)

                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Cat>
    ) {

    }


    override fun invalidate() {
        super.invalidate()
        //  job.cancel()
    }


    companion object {
        const val Page_SIZE = 10
        const val FIRST_PAGE = 1


    }
}