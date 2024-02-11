package am.hikemelikyan.specialforcypress.shared.data.api

import am.hikemelikyan.specialforcypress.model.dto.AlbumDto
import am.hikemelikyan.specialforcypress.model.dto.ImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkService {

    @GET("/albums")
    suspend fun getAlbums(): Response<List<AlbumDto>>

    @GET("/photos")
    suspend fun getImages(@Query("albumId") albumId:Int) : Response<List<ImageDto>>
}