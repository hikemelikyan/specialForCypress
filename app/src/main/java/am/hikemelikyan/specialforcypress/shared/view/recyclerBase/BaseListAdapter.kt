package am.hikemelikyan.specialforcypress.shared.view.recyclerBase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T : Any, VB : ViewBinding>() : RecyclerView.Adapter<BaseViewHolder<VB, T>>() {

    protected val currentList: ArrayList<T> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB, T> {
        return inflaterByType(parent)
    }

    private fun inflaterByType(parent: ViewGroup): BaseViewHolder<VB, T> {
        return object :
            BaseViewHolder<VB, T>(inflate(LayoutInflater.from(parent.context), parent, false)) {
            override fun bind(data: T) {
                bindActionTo(data)
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    abstract fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): VB

    abstract fun BaseViewHolder<VB, T>.bindActionTo(data: T)

    override fun onBindViewHolder(holder: BaseViewHolder<VB, T>, position: Int) {
        currentList[position]?.let { holder.bind(it) }
    }

}