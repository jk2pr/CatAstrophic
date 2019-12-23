package com.jk.catastrophic.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jk.catastrophic.CatAstrophicApplication
import com.jk.catastrophic.R


import com.jk.catastrophic.ui.fragments.CatFragment.OnListFragmentInteractionListener
import com.jk.catastrophic.data.Cat
import com.jk.catastrophic.databinding.FragmentCatBinding
import com.jk.catastrophic.utils.DiffUtilCallBack
import com.squareup.picasso.Target

import kotlinx.android.synthetic.main.fragment_cat.view.*
import javax.inject.Inject

/**
 * [RecyclerView.Adapter] that can display a [Cat] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class CatRecyclerViewAdapter(

    private var requestManager: RequestManager,
    private val mListener: OnListFragmentInteractionListener?
) : PagedListAdapter<Cat, CatRecyclerViewAdapter.ViewHolder>(DiffUtilCallBack()) {

    private val mOnClickListener: View.OnClickListener


    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Cat
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil
            .inflate<FragmentCatBinding>(
                LayoutInflater.from(parent.context),
                R.layout.fragment_cat,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position)!!)
    }


    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        // holder.unBind()
    }

    inner class ViewHolder(private val binding: FragmentCatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun unBind() {
            requestManager.clear(binding.imageCat)

        }

        fun bind(catList: Cat) {
            with(binding) {
                item = catList
                requestManager = this@CatRecyclerViewAdapter.requestManager
                executePendingBindings()

            }

        }
    }

    internal companion object {

        @JvmStatic
        @BindingAdapter(value = ["requestManager", "profileImage"])
        fun loadImage(view: ImageView, requestManager: RequestManager, url: String) {
            requestManager
                .load(url)
                //.dontTransform()
                /*.override(
                    com.bumptech.glide.request.target.Target.SIZE_ORIGINAL,
                    com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
                )*/
                .into(view)
            // .diskCacheStrategy(DiskCacheStrategy.ALL)
            //  .placeholder(R.drawable.loading_spinner)

        }


    }
}
