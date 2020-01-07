package com.example.kotlinapplication.ui.view.page.kin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.KinItem
import com.example.kotlinapplication.extension.getHtmlText
import com.example.kotlinapplication.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_kin.view.*

class KinAdapter(private val listener: (KinItem) -> Unit) : BaseAdapter<KinItem, KinAdapter.KinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinViewHolder =
        KinViewHolder(parent, listener)

    override fun onBindViewHolder(holder: KinViewHolder, position: Int) =
        holder.bind(items[position])

    class KinViewHolder(parent: ViewGroup, private val listener: (KinItem) -> Unit) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_kin,
                parent,
                false
            )
        ) {

        private lateinit var item: KinItem

        init {
            itemView.setOnClickListener {
                listener(item)
            }
        }

        fun bind(item: KinItem) {
            this.item = item
            with(itemView) {
                textview_kin_title.text = item.title.getHtmlText()
                textview_kin_description.text = item.description.getHtmlText()
            }
        }
    }
}