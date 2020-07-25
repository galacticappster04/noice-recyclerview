package com.neonapps.lib.android.noice.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.neonapps.lib.android.noice.rv.adapter.holder.TypedHolder
import com.neonapps.lib.android.noice.rv.adapter.item.AdapterItem

class ReusableAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val prototypes : HashMap<Int, TypedHolder.Provider> = hashMapOf()

    private var _content : MutableList<AdapterItem> = mutableListOf()

    var multiSelectionEnabled : Boolean = false
        set(value){
            field = value
            notifyDataSetChanged()
        }

    var owner : LifecycleOwner? = null

    var notifyCurrentSelectedOnChange : Boolean = false

    val content : List<AdapterItem>
        get() = _content

    val selectedItems : Map<Int, AdapterItem>
        get() {
            val items : MutableMap<Int, AdapterItem> = mutableMapOf()

            content.withIndex().forEach{
                if(it.value.isSelected)
                    items.put(it.index, it.value)
            }

            return items
        }

    var currentSelected : Int = -1
        set(value){

            previousSelected = if(field == -1) value else field
            field = value

            if(notifyCurrentSelectedOnChange)
                notifyItemChanged(currentSelected)

            notifyItemChanged(previousSelected)
        }

    private var previousSelected : Int = -1
    private var linearLayoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(!prototypes.containsKey(viewType)) {
            throw Error("There is no available viewholder prototype for type= $viewType")
        }

        if(linearLayoutInflater == null)
            linearLayoutInflater = LayoutInflater.from(parent.context)

        return prototypes[viewType]?.create(linearLayoutInflater!!, parent, false)!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        content[position].bind(holder, position)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        content.getOrNull(holder.adapterPosition)?.onViewDetachedFromWindow(holder)
    }

    // TODO Use DiffUtils here
    fun setContent(items : List<AdapterItem>) {

        prototypes.clear()

        for(item in items){
            if(!prototypes.containsKey(item.type))
                prototypes[item.type] = item.createProvider()

            item.adapter = this
        }

        this._content = items.toMutableList()
        notifyDataSetChanged()
    }

    fun addContent(vararg item : AdapterItem) {
        initItems(*item)
        this._content.addAll(item)
        notifyItemRangeInserted(this._content.size - 1, item.size)
    }

    fun addContent(index : Int, vararg item : AdapterItem) {
        initItems(*item)
        this._content.addAll(index, item.toList())
        notifyItemRangeInserted(index, item.size)
    }

    private fun initItems(vararg item : AdapterItem) {
        for(i in item) {
            if(!prototypes.containsKey(i.type))
                prototypes[i.type] = i.createProvider()

            i.adapter = this
            i.onAdded()
        }
    }

    fun update(index : Int, payload : Any) {
        if(index < 0 || index >= _content.size)
            return

        val item = _content[index]
        item.update(payload)
        notifyItemChanged(index)
    }

    fun getItem(index : Int) : AdapterItem? = _content.getOrNull(index)

    val size : Int = _content.size

    // TODO Prefer DiffUtils instead
//    fun addItem(position: Int = this@AppSimpleAdapter.content.size - 1, item: AdapterItem<V>) {
//        if(!prototypes.containsKey(item.type))
//            prototypes[item.type] = item.createPrototype()
//
//        item.adapter = this
//        _content.add(position, item)
//        notifyItemInserted(position)
//    }

    fun removeItem(vararg position : Int) {
        val selectedPositions = position.filter { it > 0 || it < content.size }
        val selectedItems = _content.withIndex().filter { it.index in selectedPositions }.map { it.value }

        selectedItems.forEach {
            // it.adapter = null
            _content.remove(it)
        }

        selectedPositions.forEach {
            notifyItemRemoved(it)
        }
    }

    fun clear() {
        _content.clear()
        prototypes.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = content.size

    override fun getItemViewType(position: Int): Int = content[position].type

    override fun getItemId(position: Int): Long = content[position].getItemId()

    companion object{

        private var idGenerator : Long = 0

        fun getItemId() : Long = idGenerator++
    }
}