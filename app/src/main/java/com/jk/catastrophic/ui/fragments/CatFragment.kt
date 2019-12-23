package com.jk.catastrophic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
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
            layoutManager = GridLayoutManager(
                context,
                3
                // StaggeredGridLayoutManager.VERTICAL
            )
            adapter = CatRecyclerViewAdapter(
                requestManager,
                listener
            )

        }
        catFragmentViewModel.catPagedList.observe(this, Observer<PagedList<Cat>>{

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
