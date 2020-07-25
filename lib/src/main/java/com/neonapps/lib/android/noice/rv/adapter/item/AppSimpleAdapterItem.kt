package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.ReusableAdapter

abstract class AppSimpleAdapterItem<V> : AdapterItem<V> {

    protected val _itemId : Long = ReusableAdapter.getItemId()

    override lateinit var adapter : ReusableAdapter<V>

    override fun getItemId(): Long = _itemId
}