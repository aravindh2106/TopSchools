package screens.addschooldetails

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import database.School
import database.SchoolDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddSchoolDetailsScreenModel(private val schoolDao: SchoolDao) : ScreenModel {

    private val _schoolView = MutableStateFlow(SchoolView())
    val schoolView: StateFlow<SchoolView> get() = _schoolView

    private val _schoolErrorView = MutableStateFlow(SchoolErrorView())
    val schoolErrorView: StateFlow<SchoolErrorView> get() = _schoolErrorView

    private val _isSuccess: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> get() = _isSuccess

    fun updateErrorState(setupErrorView: SchoolErrorView) {
        _schoolErrorView.value = setupErrorView
    }

    fun saveSchool() {

        screenModelScope.launch {
            when {
                _schoolView.value.schoolName.isNullOrEmpty() -> {
                    _schoolErrorView.value =
                        SchoolErrorView(
                            schoolNameError = "School name shouldn't be a blank",
                            isSchoolNameError = true
                        )
                }

                _schoolView.value.description.isNullOrEmpty() -> {
                    _schoolErrorView.value =
                        SchoolErrorView(
                            descriptionError = "Description shouldn't be a blank",
                            isDescriptionNameError = true
                        )
                }

                _schoolView.value.city.isNullOrEmpty() -> {
                    _schoolErrorView.value =
                        SchoolErrorView(
                            cityError = "City shouldn't be a blank",
                            isCityNameError = true
                        )
                }

                else -> {
                    schoolDao.upsert(
                        School(
                            schoolName = _schoolView.value.schoolName!!,
                            description = _schoolView.value.description!!,
                            city = _schoolView.value.city!!
                        )
                    )
                    _isSuccess.value = true
                }
            }

        }
    }

    fun updateData(schoolView: SchoolView) {
        _schoolView.value = schoolView
    }
}

data class SchoolView(
    var schoolName: String? = null,
    var description: String? = null,
    var city: String? = null
)

data class SchoolErrorView(
    var schoolNameError: String? = null,
    var descriptionError: String? = null,
    var cityError: String? = null,
    var isSchoolNameError: Boolean = false,
    var isDescriptionNameError: Boolean = false,
    var isCityNameError: Boolean = false
)