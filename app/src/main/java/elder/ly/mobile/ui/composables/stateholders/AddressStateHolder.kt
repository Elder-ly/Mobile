package elder.ly.mobile.ui.composables.stateholders

import elder.ly.mobile.domain.service.AddressOutput

sealed class AddressStateHolder {
    data object Loading : AddressStateHolder()

    data class Content(
        val data: AddressOutput // Alterado para receber um único usuário
    ) : AddressStateHolder()

    data class Error(
        val message: String
    ) : AddressStateHolder()
}