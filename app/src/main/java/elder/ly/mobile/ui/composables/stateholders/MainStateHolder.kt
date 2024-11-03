package elder.ly.mobile.ui.composables.stateholders

import elder.ly.mobile.domain.service.GetUsersOutput

sealed class MainStateHolder {
    data object Loading : MainStateHolder()

    data class Content(
        val data: GetUsersOutput // Alterado para receber um único usuário
    ) : MainStateHolder()

    data class Error(
        val message: String
    ) : MainStateHolder()
}