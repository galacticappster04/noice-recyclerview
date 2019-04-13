package com.neonapps.lib.android.noice.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem

// TODO upload project to Github and deploy on jcenter
// TODO add evaluate method which calls getViewPrototype() on each item
// TODO make another parameterless constructor
// TODO throw exception when parameterless constructor called but evaluate has not been called
class AppSimpleAdapter<V>(private val prototypes : Map<Int, TypedHolder.Prototype>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class Event { Clicked }

    interface Listener<V> {
        fun onEventTriggered(item : AppSimpleAdapterItem<V>, position : Int, eventName : String)
    }

    var content : List<AppSimpleAdapterItem<V>> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    var currentSelected : Int = -1
        set(value){
            previousSelected = field
            field = value
            notifyItemChanged(currentSelected)
            notifyItemChanged(previousSelected)
        }

    var listener : Listener<V>? = null

    private var previousSelected : Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(!prototypes.containsKey(viewType)) {
            throw Error("There is no available viewholder prototype for type= $viewType")
        }

        return prototypes[viewType]?.create(LayoutInflater.from(parent.context), parent, false)!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        content[position].bind(this, holder, position)
    }

    fun dispatchOnClickEvent(eventType : Event, item : AppSimpleAdapterItem<V>, position : Int, eventName : String){
        when (eventType){
            Event.Clicked -> {
                listener?.onEventTriggered(item, position, eventName)
            }
        }
    }

    override fun getItemCount(): Int = content.size

    override fun getItemViewType(position: Int): Int = content[position].type
}