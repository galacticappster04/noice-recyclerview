package com.neonapps.lib.android.noice.sample.visitor

interface SampleItemVisitor {
    fun onBind(position : Int, eventName : String)
}