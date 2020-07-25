package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.ReusableAdapter
import com.neonapps.lib.android.noice.rv.adapter.events.*

interface AdapterItem : ItemBindable, MultiSelectable, Updateable, Provider {
    val type : Int

    var adapter : ReusableAdapter

    fun onAdded() {}

    fun getItemId() : Long
}