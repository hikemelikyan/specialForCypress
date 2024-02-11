package am.hikemelikyan.specialforcypress.shared.view.recyclerBase

interface DifItem<T> {

	fun areItemsTheSame(second : T) : Boolean
	fun areContentsTheSame(second : T) : Boolean

}
