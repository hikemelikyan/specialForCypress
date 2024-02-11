package am.hikemelikyan.specialforcypress.mvvm.vm

import androidx.annotation.StringRes

sealed class Commands {

	/**
	 * base UI commands
	 * */
	object NetworkError : ViewCommand
	class ShowMessage(@StringRes val resId : Int) : ViewCommand
	class ShowMessageText(val errorMessage : String) : ViewCommand
	object StateLoading : ViewCommand
	object StateEmpty : ViewCommand
}