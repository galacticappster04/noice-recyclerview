package com.neonapps.lib.android.noice.rv.adapter.item

import com.neonapps.lib.android.noice.rv.adapter.ReusableAdapter

abstract class AppSimpleAdapterItem : AdapterItem {

    protected val _itemId : Long = ReusableAdapter.getItemId()

    override lateinit var adapter : ReusableAdapter

    override fun getItemId(): Long = _itemId
}