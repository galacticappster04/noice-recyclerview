package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter
import com.neonapps.lib.android.noice.rv.adapter.events.*

interface AdapterItem<T> : ItemBindable<T>, MultiSelectable<T>, Updateable, Provider {
    val type : Int

    var adapter : AppSimpleAdapter<T>

    fun getItemId() : Long
}