package com.neonapps.lib.android.noice.rv.adapter.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class TypedHolder(val view : View) : RecyclerView.ViewHolder(view) {
    interface Provider {
        fun create(inflater : LayoutInflater, group : ViewGroup, attachToParent : Boolean) : TypedHolder
    }
}

