package am.hikemelikyan.specialforcypress.shared.view.recyclerBase

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<VB : ViewBinding, DATA : Any>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {

    val holderContext: Context = binding.root.context

    abstract fun bind(data: DATA)
}