package com.neonapps.lib.android.noice.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem

// TODO throw exception when parameterless constructor called but evaluate has not been called
class AppSimpleAdapter<V> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class Event { Clicked, ViewBound }

    private val prototypes : MutableMap<Int, TypedHolder.Prototype> = mutableMapOf()

    interface Listener<V> {
        fun onClicked(item : AppSimpleAdapterItem<V>, position : Int, eventName : String)

        fun onViewBound(item : AppSimpleAdapterItem<V>, viewHolder : RecyclerView.ViewHolder, position : Int, eventName : String)
    }

    private var _content : MutableList<AppSimpleAdapterItem<V>> = mutableListOf()

    val content : List<AppSimpleAdapterItem<V>>
        get() = _content

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

    fun dispatchEvent(eventType : Event, item : AppSimpleAdapterItem<V>, viewHolder : RecyclerView.ViewHolder, position : Int, eventName : String){
        when (eventType){
            Event.Clicked -> {
                listener?.onClicked(item, position, eventName)
            }

            Event.ViewBound -> {
                listener?.onViewBound(item, viewHolder, position, eventName)
            }
        }
    }

    fun setContent(items : MutableList<AppSimpleAdapterItem<V>>) {

        for(item in items){
            if(!prototypes.containsKey(item.type))
                prototypes[item.type] = item.createPrototype()
        }

        this._content = items
        notifyDataSetChanged()
    }

    fun addItem(position: Int = this@AppSimpleAdapter.content.size - 1, item: AppSimpleAdapterItem<V>) {
        if(!prototypes.containsKey(item.type))
            prototypes[item.type] = item.createPrototype()

        _content.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItem(position : Int) {
        if(position < 0 || position >= content.size)
            return

        val removedItem = _content.removeAt(position)
        prototypes.remove(removedItem.type)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = content.size

    override fun getItemViewType(position: Int): Int = content[position].type
}