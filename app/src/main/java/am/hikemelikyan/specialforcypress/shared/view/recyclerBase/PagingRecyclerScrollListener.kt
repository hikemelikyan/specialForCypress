package am.hikemelikyan.specialforcypress.shared.view.recyclerBase

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingRecyclerScrollListener(private val onLimitReachedAction : () -> Unit) : RecyclerView.OnScrollListener(){
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val lm = recyclerView.layoutManager as LinearLayoutManager
        val all = lm.itemCount
        val last = lm.findLastCompletelyVisibleItemPosition()
        if (last == all - 1) {
            onLimitReachedAction()
        }
    }
}