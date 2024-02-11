package am.hikemelikyan.specialforcypress.mvvm.vm

import am.hikemelikyan.specialforcypress.shared.SingleLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO

    protected val _viewCommands = SingleLiveData<ViewCommand>()
    val viewCommands : LiveData<ViewCommand>
        get() = _viewCommands

}