package screens.schoollist

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import database.School
import database.SchoolDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SchoolListScreenModel(private val schoolDao: SchoolDao) : ScreenModel {

    private val _schools = MutableStateFlow<List<School>>(emptyList())
    val schools: StateFlow<List<School>> get() = _schools

    init {
        screenModelScope.launch {
            schoolDao.getAllSchool().collect { schools ->
                _schools.value = schools
            }
        }
    }
}