package elder.ly.mobile.ui.composables.stateholders

import elder.ly.mobile.domain.service.GetUsersOutput

sealed class UserStateHolder {
    data object Loading : UserStateHolder()

    data class Content(
        val data: GetUsersOutput // Alterado para receber um único usuário
    ) : UserStateHolder()

    data class Error(
        val message: String
    ) : UserStateHolder()
}