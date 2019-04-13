package com.neonapps.lib.android.noice.rv.adapter.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class TypedHolder(val view : View, val type : Int = -1) : RecyclerView.ViewHolder(view) {
    interface Prototype {
        fun create(inflater : LayoutInflater, group : ViewGroup, attachToParent : Boolean) : TypedHolder
    }
}

