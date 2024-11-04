package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.specialtie.ISpecialtieRepository
import elder.ly.mobile.data.repository.specialtie.SpecialtieRepository
import elder.ly.mobile.data.repository.specialtie.SpecialtieRepositoryLocalImpl
import elder.ly.mobile.domain.model.Specialtie
import kotlinx.coroutines.launch

class SearchViewModel(
    private val specialtieRepository: ISpecialtieRepository
) : ViewModel() {

    private val _specialties = MutableLiveData<List<String>>()
    val specialties: LiveData<List<String>> get() = _specialties

    init {
        getSpecialties()
    }

    fun getSpecialties() {
        viewModelScope.launch {
            try {
                val response = specialtieRepository.getSpecialties()
                if (response.isSuccessful) {
                    // Mapeia as especialidades para uma lista de nomes
                    val specialtyNames = response.body()?.map { it.name } ?: emptyList()
                    _specialties.value = specialtyNames
                } else {
                    // Trate a resposta de erro se necessário
                    _specialties.value = emptyList()
                }
            } catch (e: Exception) {
                // Trate a exceção se necessário
                _specialties.value = emptyList()
            }
        }
    }
}