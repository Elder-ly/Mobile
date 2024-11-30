package elder.ly.mobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.proposal.IProposalRepository
import elder.ly.mobile.domain.service.GetDataProposalScreen
import elder.ly.mobile.domain.service.MessageWithProposalInput
import elder.ly.mobile.domain.service.ProposalInput
import elder.ly.mobile.utils.DateTimeUtils.convertToIso8601
import kotlinx.coroutines.launch

class ProposalViewModel(
    private val proposalRepository: IProposalRepository
) : ViewModel() {

    var senderId by mutableLongStateOf(-1L)

    var recipientId by mutableLongStateOf(-1L)

    fun sendProposal(getDataProposalScreen: GetDataProposalScreen) {
        if (senderId > 0 && recipientId > 0) {
            viewModelScope.launch {
                val response = proposalRepository.createProposal(
                    MessageWithProposalInput(
                        senderId,
                        recipientId,
                        getDataProposalScreen.titulo,
                        ProposalInput(
                            dataHoraInicio = convertToIso8601(getDataProposalScreen.startDate + getDataProposalScreen.startTime),
                            dataHoraFim = convertToIso8601(getDataProposalScreen.endDate + getDataProposalScreen.endTime),
                            preco = getDataProposalScreen.preco,
                            descricao = getDataProposalScreen.descricao
                        )
                    )
                )
                if (response.isSuccessful) {
                    Log.d("proposalScreen", "Proposta enviada com sucesso")
                } else {
                    Log.d("proposalScreen", "Erro ao enviar proposta: ${response.message()}")
                }
            }
        }
    }

}