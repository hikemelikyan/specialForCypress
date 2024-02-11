package am.hikemelikyan.specialforcypress.shared.data.api

import am.hikemelikyan.specialforcypress.model.dto.AlbumDto
import am.hikemelikyan.specialforcypress.model.dto.ImageDto
import am.hikemelikyan.specialforcypress.shared.data.networking.root.BaseService
import am.hikemelikyan.specialforcypress.shared.data.networking.root.networkProvider

class NetworkService : BaseService{

    private val mService: INetworkService by networkProvider()
    companion object {
        private lateinit var INSTANCE: NetworkService

        fun getInstance(): NetworkService {
            if (!Companion::INSTANCE.isInitialized) INSTANCE = NetworkService()
            return INSTANCE
        }
    }

    suspend fun getAllAlbums(): List<AlbumDto>?{
        return proceed { mService.getAlbums() }
    }

    suspend fun getAllImagesByAlbumId(albumId:Int): List<ImageDto> {
        return mService.getImages(albumId).body() ?: emptyList()
    }

}