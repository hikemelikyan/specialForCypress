package am.hikemelikyan.specialforcypress.ui.mainActivity.adapter

import am.hikemelikyan.specialforcypress.databinding.AdapterHorizontalImageItemBinding
import am.hikemelikyan.specialforcypress.databinding.AdapterHorizontalImagesBinding
import am.hikemelikyan.specialforcypress.shared.view.recyclerBase.BaseListAdapter
import am.hikemelikyan.specialforcypress.shared.view.recyclerBase.BaseViewHolder
import am.hikemelikyan.specialforcypress.shared.view.recyclerBase.getDiffCallback
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImagesHorizontalAdapter : BaseListAdapter<String, AdapterHorizontalImageItemBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        attachToParent: Boolean
    ): AdapterHorizontalImageItemBinding {
        return AdapterHorizontalImageItemBinding.inflate(LayoutInflater.from(parent?.context))
    }

    override fun BaseViewHolder<AdapterHorizontalImageItemBinding, String>.bindActionTo(data: String) {
        Glide.with(holderContext)
            .load(data)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.idImageLoader)
    }

    fun updateList(list:List<String>){
        currentList.addAll(list)
        notifyItemRangeInserted(currentList.size - list.size, list.size)
    }

}