package elder.ly.mobile.ui.composables.stateholders

import elder.ly.mobile.domain.model.User

sealed class MainStateHolder {
    data object Loading : MainStateHolder()

    data class Content(
        val data : List<User>
    ) : MainStateHolder()

    data class Error(
        val message : String
    ) : MainStateHolder()
}