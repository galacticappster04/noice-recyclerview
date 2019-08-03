package com.neonapps.lib.android.noice.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.neonapps.lib.android.noice.rv.adapter.AppSimpleAdapter
import com.neonapps.lib.android.noice.rv.adapter.item.AppSimpleAdapterItem
import com.neonapps.lib.android.noice.sample.entities.DoubleEntity
import com.neonapps.lib.android.noice.sample.entities.FloatEntity
import com.neonapps.lib.android.noice.sample.entities.IntEntity
import com.neonapps.lib.android.noice.sample.entities.StringEntity
import com.neonapps.lib.android.noice.sample.items.DoubleItem
import com.neonapps.lib.android.noice.sample.items.FloatItem
import com.neonapps.lib.android.noice.sample.items.IntItem
import com.neonapps.lib.android.noice.sample.items.StringItem
import com.neonapps.lib.android.noice.sample.visitor.SampleItemVisitor
import kotlinx.android.synthetic.main.activity_main.*

// TODO Create sample for this project
class MainActivity : AppCompatActivity() {

    private val adapter : AppSimpleAdapter<SampleItemVisitor> = AppSimpleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items : MutableList<AppSimpleAdapterItem<SampleItemVisitor>> = mutableListOf()
        items.add(DoubleItem(DoubleEntity((1.0))))
        items.add(DoubleItem(DoubleEntity((2.0))))
        items.add(StringItem(StringEntity("Hello World 1")))
        items.add(StringItem(StringEntity("Hello World 2")))
        items.add(StringItem(StringEntity("Hello World 3")))
        items.add(StringItem(StringEntity("Hello World 4")))
        items.add(StringItem(StringEntity("Hello World 5")))
        items.add(IntItem(IntEntity(1)))
        items.add(IntItem(IntEntity(2)))
        items.add(IntItem(IntEntity(3)))
        items.add(IntItem(IntEntity(4)))
        items.add(FloatItem(FloatEntity(5f)))
        items.add(FloatItem(FloatEntity(4f)))
        items.add(FloatItem(FloatEntity(3f)))

        recyclerview_main.adapter = adapter
        recyclerview_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.setContent(items)
    }
}
