package io.github.alessandrojean.toshokan.presentation.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.alessandrojean.toshokan.database.data.Person
import io.github.alessandrojean.toshokan.presentation.ui.people.manage.ManagePeopleMode
import io.github.alessandrojean.toshokan.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PeopleState(
  val people: Flow<List<Person>>,
  val showManageDialog: Boolean = false,
  val showDeleteWarning: Boolean = false,
  val selected: List<Long> = emptyList(),
  val manageDialogMode: ManagePeopleMode = ManagePeopleMode.CREATE
)

@HiltViewModel
class PeopleViewModel @Inject constructor(
  private val peopleRepository: PeopleRepository
) : ViewModel() {
  private val _uiState = MutableStateFlow(
    PeopleState(
      people = peopleRepository.people
    )
  )
  val uiState: StateFlow<PeopleState> = _uiState.asStateFlow()

  fun changeManageDialogMode(newMode: ManagePeopleMode) = viewModelScope.launch {
    _uiState.update { it.copy(manageDialogMode = newMode) }
  }

  fun showManageDialog() = viewModelScope.launch {
    _uiState.update { it.copy(showManageDialog = true) }
  }

  fun hideManageDialog() = viewModelScope.launch {
    _uiState.update {
      it.copy(
        showManageDialog = false,
        selected = emptyList(),
        manageDialogMode = ManagePeopleMode.CREATE
      )
    }
  }

  fun showDeleteWarning() = viewModelScope.launch {
    _uiState.update { it.copy(showDeleteWarning = true) }
  }

  fun hideDeleteWarning() = viewModelScope.launch {
    _uiState.update { it.copy(showDeleteWarning = false) }
  }

  fun clearSelection() = viewModelScope.launch {
    _uiState.update { it.copy(selected = emptyList()) }
  }

  fun toggleSelection(id: Long) = viewModelScope.launch {
    if (id !in uiState.value.selected) {
      _uiState.update { it.copy(selected = uiState.value.selected + id) }
    } else {
      _uiState.update {
        it.copy(
          selected = uiState.value.selected.filter { selId -> selId != id }
        )
      }
    }
  }

  fun deleteSelected() = viewModelScope.launch {
    peopleRepository.bulkDelete(uiState.value.selected)
    clearSelection()
  }
}