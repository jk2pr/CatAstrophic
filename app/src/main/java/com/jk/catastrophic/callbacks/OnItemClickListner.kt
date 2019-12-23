package com.jk.catastrophic.callbacks

import android.view.View
import com.jk.catastrophic.data.Cat

interface OnItemClickListner {
    fun onItemClick(view: View, item: Cat)
}