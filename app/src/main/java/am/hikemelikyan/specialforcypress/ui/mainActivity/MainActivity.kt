package am.hikemelikyan.specialforcypress.ui.mainActivity

import am.hikemelikyan.specialforcypress.databinding.ActivityMainBinding
import am.hikemelikyan.specialforcypress.mvvm.BaseRequestActivity
import am.hikemelikyan.specialforcypress.mvvm.vm.Commands
import am.hikemelikyan.specialforcypress.mvvm.vm.ViewCommand
import am.hikemelikyan.specialforcypress.shared.data.networking.root.isInternetAvailable
import am.hikemelikyan.specialforcypress.shared.view.recyclerBase.PagingRecyclerScrollListener
import am.hikemelikyan.specialforcypress.ui.mainActivity.adapter.HomeAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : BaseRequestActivity<ActivityMainBinding, MainViewModel>() {
    private lateinit var mAdapter: HomeAdapter

    override val viewModelType: Class<MainViewModel>
        get() = MainViewModel::class.java

    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun initView(binding: ActivityMainBinding, viewModel: MainViewModel) {
        mAdapter = HomeAdapter()
        binding.rvHome.adapter = mAdapter
        binding.rvHome.addOnScrollListener(PagingRecyclerScrollListener{
            mViewModel.loadMore()
        })
        mViewModel.categories.observe(this) {
            mAdapter.updateList(it)
        }
    }

    override fun proceedViewCommand(command: ViewCommand) {
        when (command) {
            is Commands.StateLoading -> {
                mBinding.rvHome.visibility = View.GONE
                mBinding.pbVisible.visibility = View.VISIBLE
            }
            else -> {
                mBinding.rvHome.visibility = View.VISIBLE
                mBinding.pbVisible.visibility = View.GONE
            }
        }
    }
}
