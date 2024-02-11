package am.hikemelikyan.specialforcypress.shared.data.networking.root

import retrofit2.Response
import java.lang.Exception

interface BaseService {

    suspend fun <R> proceed(action: suspend () -> Response<R>): R? {
        return if(isInternetAvailable()){
            action().body()
        }else{
            null
        }
    }

}