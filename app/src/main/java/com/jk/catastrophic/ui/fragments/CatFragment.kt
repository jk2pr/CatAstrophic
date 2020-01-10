package com.jk.catastrophic.ui.fragments

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.RequestManager
import com.jk.catastrophic.CatAstrophicApplication
import com.jk.catastrophic.R
import com.jk.catastrophic.data.Cat
import com.jk.catastrophic.service.ICatApi
import com.jk.catastrophic.ui.adapters.CatRecyclerViewAdapter
import com.jk.catastrophic.viewmodels.CatFragmentViewModel
import com.jk.catastrophic.viewmodels.MyViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cat_list.*
import javax.inject.Inject


class CatFragment : DaggerFragment() {


    private val listener: OnListFragmentInteractionListener by lazy {

        context as OnListFragmentInteractionListener
    }


    private lateinit var catFragmentViewModel: CatFragmentViewModel
    //  private lateinit var catLiveData: LiveData<PagedList<Cat>>

    @Inject
    lateinit var iCatApi: ICatApi
    @Inject
    lateinit var requestManager: RequestManager
    @Inject
    lateinit var application: CatAstrophicApplication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catFragmentViewModel = ViewModelProviders.of(
            this,
            MyViewModelFactory(application = application, param = iCatApi)
        ).get(CatFragmentViewModel::class.java)


        //   catLiveData = catFragmentViewModel.catPagedList
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_cat_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Set the adapter
        with(list) {
            layoutManager = StaggeredGridLayoutManager(
                3,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = CatRecyclerViewAdapter(
                requestManager,
                listener
            )

        }


       /* list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager

                var firstvisibleArray = intArrayOf(0, 0, 0)
                var lastvisibleArray = intArrayOf(0, 0, 0)
                layoutManager.findFirstVisibleItemPositions(firstvisibleArray)
                layoutManager.findLastVisibleItemPositions(lastvisibleArray)


                print(firstvisibleArray)
                print(lastvisibleArray)

                val first = firstvisibleArray[0]
                val last = lastvisibleArray[lastvisibleArray.size - 1]

                val rvRect = Rect()
                recyclerView.getGlobalVisibleRect(rvRect);
                val adapter = (recyclerView.adapter as CatRecyclerViewAdapter)
                for (k in first..last) {
                    val percentage =
                        getVisibleHeightPercentage(layoutManager.findViewByPosition(k)!!)
                    Log.d("Percentage  $k = ", percentage.toString())

                    adapter.setPercentage(
                        k,
                        percentage
                    )


                }





        }
    })*/


    catFragmentViewModel.catPagedList.observe(this, Observer<PagedList<Cat>>
    {

        (list.adapter as CatRecyclerViewAdapter).apply {
            print("Callback from Observer")
            submitList(it)
            /* when (it.status) {
                 CatResource.AuthStatus.LOADING -> {
                     showProgress(true)
                     Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()


                 }
                 else ->
                     //  CatResource.AuthStatus.AUTHENTICATED ->
                 {
                     showProgress(false)

                     addAll(it.data)
                     notifyDataSetChanged()
                 }
             }*/

        }

    }
    /*Observer<CatResource<CatResource<List<Cat>>>> {
        (list.adapter as CatRecyclerViewAdapter).apply {
            when (it.status) {
                CatResource.AuthStatus.LOADING -> {
                    showProgress(true)
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()


                }
                else ->
                    //  CatResource.AuthStatus.AUTHENTICATED ->
                {
                    showProgress(false)

                    addAll(it.data)
                    notifyDataSetChanged()
                }


            }


        }


    }*/)


}


private fun getVisibleHeightPercentage(view: View): Double {

    val itemRect = Rect()
    val isParentViewEmpty = view.getLocalVisibleRect(itemRect)

    // Find the height of the item.
    val visibleHeight = itemRect.height().toDouble()
    val height = view.measuredHeight

    val viewVisibleHeightPercentage = visibleHeight / height * 100

    return if (isParentViewEmpty) {
        viewVisibleHeightPercentage
    } else {
        0.0
    }
}

private fun showProgress(isVisible: Boolean) {
    if (isVisible)
        progressBar.visibility = View.VISIBLE
    else
        progressBar.visibility = View.GONE

}

interface OnListFragmentInteractionListener {
    // TODO: Update argument type and name
    fun onListFragmentInteraction(item: Cat?)
}


}
