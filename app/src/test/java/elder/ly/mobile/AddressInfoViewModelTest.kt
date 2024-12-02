package elder.ly.mobile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import elder.ly.mobile.data.repository.addresses.IAddressRepository
import elder.ly.mobile.domain.service.AddressOutput
import elder.ly.mobile.domain.service.UpdateAddressInput
import elder.ly.mobile.ui.composables.stateholders.AddressStateHolder
import elder.ly.mobile.ui.viewmodel.AddressInfoViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class AddressInfoViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var addressRepository: IAddressRepository

    private lateinit var viewModel: AddressInfoViewModel

    @Mock
    private lateinit var observer: Observer<AddressStateHolder>

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AddressInfoViewModel(addressRepository)
        viewModel.addressCreationStatus.observeForever(observer) // Registrar o observer
    }

    @After
    fun tearDown() {
        viewModel.addressCreationStatus.removeObserver(observer) // Remover o observer
        Dispatchers.resetMain()
    }

    @Test
    fun `updateAddress() deve retornar sucesso quando o repositório responde com sucesso`() = runTest {
        val updateInput = UpdateAddressInput(
            cep = "12345-678",
            logradouro = "Rua Nova",
            complemento = "Apto 2",
            bairro = "Centro",
            numero = "15",
            cidade = "Cidade Nova",
            uf = "NN"
        )
        val addressOutput = AddressOutput(
            id = 1L,
            cep = "12345-678",
            logradouro = "Rua Nova",
            complemento = "Apto 2",
            bairro = "Centro",
            numero = "15",
            cidade = "Cidade Nova",
            uf = "NN"
        )
        `when`(addressRepository.updateAddresses(1L, updateInput)).thenReturn(Response.success(addressOutput))

        viewModel.updateAddress(1L, updateInput)

        advanceUntilIdle()

        verify(observer).onChanged(AddressStateHolder.Loading)
        verify(observer).onChanged(AddressStateHolder.Content(addressOutput))
    }

    @Test
    fun `updateAddress() deve retornar erro quando o repositório responde com erro`() = runTest {
        val updateInput = UpdateAddressInput(
            cep = "12345-678",
            logradouro = "Rua Nova",
            complemento = "Apto 2",
            bairro = "Centro",
            numero = "15",
            cidade = "Cidade Nova",
            uf = "NN"
        )

        `when`(addressRepository.updateAddresses(1L, updateInput)).thenReturn(
            Response.error(500, ResponseBody.create(null, ""))
        )

        viewModel.updateAddress(1L, updateInput)

        advanceUntilIdle()

        verify(observer).onChanged(AddressStateHolder.Loading)
        verify(observer).onChanged(
            AddressStateHolder.Error("Erro: 500")
        )
    }
}