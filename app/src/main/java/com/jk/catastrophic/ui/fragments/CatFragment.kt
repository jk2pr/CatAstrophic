package com.jk.catastrophic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
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

    private val listener: OnListFragmentInteractionListener by lazy { context as OnListFragmentInteractionListener }
    private lateinit var catFragmentViewModel: CatFragmentViewModel

    @Inject
    lateinit var iCatApi: ICatApi

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var application: CatAstrophicApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catFragmentViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(application = application, param = iCatApi)
        )
            .get(CatFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_cat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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


        catFragmentViewModel.catPagedList.observe(viewLifecycleOwner, Observer<PagedList<Cat>>
        {

            (list.adapter as CatRecyclerViewAdapter).apply {
                print("Callback from Observer")
                submitList(it)
            }
        }
        )
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Cat?)
    }
}
