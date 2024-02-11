package am.hikemelikyan.specialforcypress.shared.data.networking.root

import kotlin.reflect.KProperty

class NetworkDelegate<T>(
    private val iService: Class<T>
) {
    private var mService: T? = null

    operator fun getValue(service: BaseService, int: KProperty<*>): T {
        if (mService == null) {
            mService = NetworkModule.getConnectionInstance().create(iService)
        }
        return mService!!
    }
}

inline fun <reified T> BaseService.networkProvider() = NetworkDelegate(T::class.java)