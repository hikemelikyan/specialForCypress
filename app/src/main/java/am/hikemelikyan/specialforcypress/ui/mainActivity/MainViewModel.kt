package am.hikemelikyan.specialforcypress.ui.mainActivity

import am.hikemelikyan.specialforcypress.CypressApp
import am.hikemelikyan.specialforcypress.model.AlbumModel
import am.hikemelikyan.specialforcypress.model.ImageModel
import am.hikemelikyan.specialforcypress.model.entity.AlbumEntity
import am.hikemelikyan.specialforcypress.mvvm.vm.BaseViewModel
import am.hikemelikyan.specialforcypress.mvvm.vm.Commands
import am.hikemelikyan.specialforcypress.shared.data.AlbumRepository
import am.hikemelikyan.specialforcypress.shared.utils.SharedPrefsUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    private lateinit var _repository: AlbumRepository
    private var offset = 0
    private var limit = 10
    private var maxSize = 0

    init {
        CypressApp.getInstance()?.let {
            _repository = AlbumRepository(it.applicationContext)
        }
        getAll()
    }

    private val _categories: MutableLiveData<List<Any>> by lazy { MutableLiveData() }
    val categories: LiveData<List<Any>>
        get() = _categories

    private fun getAll() {
        val remoteDataFetched =
            SharedPrefsUtil.getInstance().readBoolean(AlbumRepository.KEY_REMOTE_DATA_FETCHED)
        _viewCommands.postValue(Commands.StateLoading)
        launch {
            if (remoteDataFetched) {
                loadData()
            } else {
                try {
                    _repository.getRemoteData()
                    loadData()
                } catch (e: Exception) {
                    _viewCommands.postValue(Commands.NetworkError)
                }
            }
        }

    }

    private fun loadData() {
        val info = arrayListOf<Any>()
        //read from db
        val localData = transformData(_repository.getLocalData(offset, limit))
        notifyUpdates(localData)
        if (maxSize == 0) {
            maxSize = _repository.getLocalDataSize()
        }
        offset += limit
        if (maxSize - offset < limit) {
            offset = 0
        }
    }

    private fun transformData(initialData: List<AlbumEntity>): List<Any> {
        val info = arrayListOf<Any>()
        initialData.map {
            info.add(AlbumModel(it.title))
            val images = it.images.split(",")
            info.add(ImageModel(images))
        }
        return info
    }

    private fun notifyUpdates(list: List<Any>) {
        _categories.postValue(list)
        _viewCommands.postValue(Commands.StateEmpty)
    }

    fun loadMore() {
        launch {
            loadData()
        }
    }
}