package com.jk.catastrophic.callbacks

import android.view.View
import com.jk.catastrophic.data.Cat

interface OnItemClickListener {
    fun onItemClick(view: View, item: Cat)
}