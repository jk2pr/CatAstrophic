package com.jk.catastrophic.utils

import androidx.recyclerview.widget.DiffUtil
import com.jk.catastrophic.data.Cat

class DiffUtilCallBack : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.url == newItem.url
                && oldItem.height == newItem.height
                && oldItem.width == newItem.width
    }

}