package am.hikemelikyan.specialforcypress.shared.data

import am.hikemelikyan.specialforcypress.model.entity.AlbumEntity
import am.hikemelikyan.specialforcypress.shared.data.api.NetworkService
import am.hikemelikyan.specialforcypress.shared.data.local.LocalAlbumStorage
import am.hikemelikyan.specialforcypress.shared.utils.SharedPrefsUtil
import android.content.Context
import java.lang.Exception

class AlbumRepository(private val context: Context) {

    private var mLocalStorage: LocalAlbumStorage = LocalAlbumStorage.getDatabase(context)
    private val mRemoteService = NetworkService.getInstance()

    companion object {
        const val KEY_REMOTE_DATA_FETCHED = "fetched"
    }

    suspend fun getRemoteData() {
        val list = arrayListOf<AlbumEntity>()
        val response = mRemoteService.getAllAlbums()
        response?.forEach {
            val sp = StringBuilder()
            mRemoteService.getAllImagesByAlbumId(it.id).forEach { image ->
                if (sp.isEmpty()) {
                    sp.append(image.thumbnailUrl)
                } else {
                    sp.append("," + image.thumbnailUrl)
                }
            }
            list.add(AlbumEntity(it.id, it.title, sp.toString()))
        } ?: throw Exception()
        storeRemoteData(list)
        SharedPrefsUtil.getInstance(context).writeBoolean(KEY_REMOTE_DATA_FETCHED, true)
    }

    fun getLocalData(offset: Int, limit: Int): List<AlbumEntity> {
        return mLocalStorage.imageDao().getPagedEntities(offset, limit)
    }

    fun getLocalDataSize(): Int {
        return mLocalStorage.imageDao().getAllEntities().size
    }

    private suspend fun storeRemoteData(list: List<AlbumEntity>) {
        list.forEach {
            mLocalStorage.imageDao().insert(it)
        }
    }

}