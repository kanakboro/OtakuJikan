package com.otakujikan.domain.model.base

import com.otakujikan.binding.RecyclerAdapter

abstract class AbstractModel {
    var adapterPosition: Int = -1
    var onItemClick: RecyclerAdapter.OnItemClick? = null

}