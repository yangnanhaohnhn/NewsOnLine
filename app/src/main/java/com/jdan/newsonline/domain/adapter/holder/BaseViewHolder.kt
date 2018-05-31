package com.jdan.newsonline.domain.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    init {
        ButterKnife.bind(this, itemView!!)
    }
}