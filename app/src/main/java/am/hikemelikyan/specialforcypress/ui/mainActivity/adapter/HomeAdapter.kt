package am.hikemelikyan.specialforcypress.ui.mainActivity.adapter

import am.hikemelikyan.specialforcypress.databinding.AdapterHorizontalImagesBinding
import am.hikemelikyan.specialforcypress.databinding.AdapterTitleItemBinding
import am.hikemelikyan.specialforcypress.model.AlbumModel
import am.hikemelikyan.specialforcypress.model.ImageModel
import am.hikemelikyan.specialforcypress.shared.view.recyclerBase.BaseMultiTypeListAdapter
import am.hikemelikyan.specialforcypress.shared.view.recyclerBase.BaseViewHolder
import am.hikemelikyan.specialforcypress.shared.view.recyclerBase.PagingRecyclerScrollListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

private const val TYPE_1 = 1
private const val TYPE_2 = 2

class HomeAdapter : BaseMultiTypeListAdapter<Any, ViewBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        attachToParent: Boolean
    ): Map<Int, ViewBinding> {
        return mapOf(
            TYPE_1 to AdapterTitleItemBinding.inflate(inflater, parent, attachToParent),
            TYPE_2 to AdapterHorizontalImagesBinding.inflate(inflater, parent, attachToParent)
        )
    }

    override fun isForType(item: Any): Int {
        return when (item) {
            is AlbumModel -> TYPE_1
            is ImageModel -> TYPE_2
            else -> error("Type is not supported")
        }
    }

    override fun bindActions(): Map<Int, BaseViewHolder<ViewBinding, Any>.(data: Any) -> Unit> {
        return mapOf(
            TYPE_1 to {
                binding as AdapterTitleItemBinding
                it as AlbumModel
                binding.title.text = it.title
            },
            TYPE_2 to {
                binding as AdapterHorizontalImagesBinding
                it as ImageModel
                val adapter = ImagesHorizontalAdapter()
                binding.rvImages.addOnScrollListener(PagingRecyclerScrollListener {
                    adapter.updateList(it.src)
                })
                binding.rvImages.adapter = adapter.apply {
                    updateList(it.src)
                }
            }
        )
    }

    fun updateList(list: List<Any>) {
        currentList.addAll(list)
        notifyItemRangeInserted(currentList.size - list.size, list.size)
    }
}