package elder.ly.mobile.ui.composables.stateholders

class MainStateHolder {
    sealed class MainStateHolder {

        data object Loading : MainStateHolder()
        data class Content(
            val data: List<Any>
        ) : MainStateHolder()
        data class Error(
            val message: String
        ) : MainStateHolder()

    }
}