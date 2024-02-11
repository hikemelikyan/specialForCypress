package am.hikemelikyan.specialforcypress.shared.view

import androidx.annotation.StringRes

interface IBaseView {
    fun showToast(message: String)
    fun showToast(@StringRes resId: Int)
}