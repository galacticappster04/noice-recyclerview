package com.neonapps.lib.android.noice.sample.visitor

import com.neonapps.lib.android.noice.sample.items.DoubleItem

interface SampleItemVisitor {
    fun onBind(position : Int, eventName : String) {}

    fun onClick(item : DoubleItem, position : Int) {}
}