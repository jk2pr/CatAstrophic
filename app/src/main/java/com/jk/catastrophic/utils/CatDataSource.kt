package com.jk.catastrophic.utils

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.jk.catastrophic.data.Cat
import com.jk.catastrophic.service.ICatApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CatDataSource(private val apiClient: ICatApi) : PageKeyedDataSource<Int, Cat>() {

    private lateinit var job: Job

    init {

        addInvalidatedCallback {

            print("INValidated")
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Cat>
    ) {

        job = GlobalScope.launch(Dispatchers.Main) {
            try {
                val limit = params.requestedLoadSize.toString()
                val posts = getPosts(limit, FIRST_PAGE)
                posts?.let {
                    callback.onResult(posts.toMutableList(), 0, FIRST_PAGE + 1)
                }
            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Cat>) {
        job = GlobalScope.launch(Dispatchers.Main) {
            try {

                val limit = params.requestedLoadSize.toString()
                val page = params.key + 1

                val posts = getPosts(limit, page)
                posts?.let {
                    callback.onResult(posts.toMutableList(), params.key + 1)
                }
            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    private suspend fun getPosts(
        limit: String,
        page: Int
    ): List<Cat>? {
        val postRequest =
            apiClient.getCatsAsync(
                limit,
                page,
                "png"
            )
        val response = postRequest.await()

        return response.body()
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Cat>
    ) {
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

    companion object {
        const val Page_SIZE = 10
        const val FIRST_PAGE = 1
    }
}