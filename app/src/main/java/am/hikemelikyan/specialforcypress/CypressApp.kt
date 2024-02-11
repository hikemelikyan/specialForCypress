package am.hikemelikyan.specialforcypress

import android.app.Application
import androidx.lifecycle.MutableLiveData

class CypressApp : Application() {
    companion object {
        private var mInstance: CypressApp? = null

        fun getInstance() = mInstance
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
}