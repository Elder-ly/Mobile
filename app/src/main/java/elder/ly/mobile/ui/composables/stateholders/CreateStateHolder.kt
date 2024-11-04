package elder.ly.mobile.ui.composables.stateholders

import elder.ly.mobile.domain.service.GetUsersOutput

sealed class CreateStateHolder {
    data object Loading : CreateStateHolder()

    data class Content(
        val data: GetUsersOutput // Alterado para receber um único usuário
    ) : CreateStateHolder()

    data class Error(
        val message: String
    ) : CreateStateHolder()
}