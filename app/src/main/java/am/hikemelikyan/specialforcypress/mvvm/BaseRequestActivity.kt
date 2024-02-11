package am.hikemelikyan.specialforcypress.mvvm

import am.hikemelikyan.specialforcypress.R
import am.hikemelikyan.specialforcypress.mvvm.vm.BaseViewModel
import am.hikemelikyan.specialforcypress.mvvm.vm.Commands
import am.hikemelikyan.specialforcypress.mvvm.vm.ViewCommand
import am.hikemelikyan.specialforcypress.ui.welcomeActivity.WelcomeActivity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseRequestActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>() {

    private val _viewModel by lazy { ViewModelProvider.NewInstanceFactory().create(viewModelType) }
    val mViewModel: VM
        get() = _viewModel

    protected abstract val viewModelType: Class<VM>

    protected abstract fun initView(binding: VB, viewModel: VM)

    @Deprecated(
        "This function is deprecated for BaseRequestActivity child",
        ReplaceWith("initView(binding:VB,viewModel:VM)")
    )
    override fun initView(binding: VB) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView(mBinding, _viewModel)
        _viewModel.viewCommands.observe(this) {
            proceedInternalCommands(it)
        }
        setContentView(mBinding.root)
    }

    private fun proceedInternalCommands(command: ViewCommand) {
        when (command) {
            is Commands.NetworkError -> showToast(R.string.default_network_error_message)
            is Commands.ShowMessage -> showToast(command.resId)
            is Commands.ShowMessageText -> showToast(command.errorMessage)
            else -> proceedViewCommand(command)
        }
    }

    protected abstract fun proceedViewCommand(command: ViewCommand)

}