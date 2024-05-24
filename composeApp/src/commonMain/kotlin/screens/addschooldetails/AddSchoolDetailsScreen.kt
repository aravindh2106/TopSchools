package screens.addschooldetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import database.SchoolDao

data class AddSchoolDetailsScreen(val schoolDao: SchoolDao) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        AddSchoolDetails(schoolDao, onBackClick = { navigator.pop() })
    }
}

@Composable
fun AddSchoolDetails(schoolDao: SchoolDao, onBackClick: () -> Unit) {
    val addSchoolDetailsScreenModel = remember { AddSchoolDetailsScreenModel(schoolDao) }
    val schoolView = addSchoolDetailsScreenModel.schoolView.collectAsState().value
    val schoolErrorView = addSchoolDetailsScreenModel.schoolErrorView.collectAsState().value
    val isSuccess = addSchoolDetailsScreenModel.isSuccess.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->
        LaunchedEffect(isSuccess) {
            if (isSuccess) {
                onBackClick()
                snackbarHostState.showSnackbar(
                    message = "School details successfully saved!",
                    duration = SnackbarDuration.Short
                )

            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize().background(Color(0xFFFED8B1))
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopStart)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
            }
            Column(
                Modifier
                    .fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Fill the school details",
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    color = Color.Black,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = schoolView.schoolName ?: "",
                    onValueChange = {
                        if (schoolErrorView.isSchoolNameError) {
                            addSchoolDetailsScreenModel.updateErrorState(
                                schoolErrorView.copy(
                                    isSchoolNameError = false
                                )
                            )
                        }
                        addSchoolDetailsScreenModel.updateData(schoolView.copy(schoolName = it))
                    },
                    placeholder = { Text(text = "School name") },
                    modifier = Modifier
                        .padding(8.dp).background(Color.White),
                    trailingIcon = {
                        if (schoolErrorView.isSchoolNameError) {
                            Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colors.error)
                        }
                    },
                    isError = schoolErrorView.isSchoolNameError,

                )
                if (schoolErrorView.isSchoolNameError) {
                    Text(
                        textAlign = TextAlign.End,
                        text = schoolErrorView.schoolNameError!!,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body1,
                    )
                }
                Spacer(Modifier.width(8.dp))
                OutlinedTextField(
                    value = schoolView.description ?: "",
                    onValueChange = {
                        if (schoolErrorView.isDescriptionNameError) {
                            addSchoolDetailsScreenModel.updateErrorState(
                                schoolErrorView.copy(
                                    isDescriptionNameError = false
                                )
                            )
                        }
                        addSchoolDetailsScreenModel.updateData(schoolView.copy(description = it))
                    },
                    placeholder = { Text(text = "Description") },
                    trailingIcon = {
                        if (schoolErrorView.isDescriptionNameError) {
                            Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colors.error)
                        }
                    },
                    isError = schoolErrorView.isDescriptionNameError,
                    modifier = Modifier
                        .padding(8.dp).background(Color.White)
                )
                if (schoolErrorView.isDescriptionNameError) {
                    Text(
                        textAlign = TextAlign.End,
                        text = schoolErrorView.descriptionError!!,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body1,
                    )
                }
                Spacer(Modifier.width(8.dp))
                OutlinedTextField(
                    value = schoolView.city ?: "",
                    onValueChange = {
                        if (schoolErrorView.isCityNameError) {
                            addSchoolDetailsScreenModel.updateErrorState(
                                schoolErrorView.copy(
                                    isCityNameError = false
                                )
                            )
                        }
                        addSchoolDetailsScreenModel.updateData(schoolView.copy(city = it))
                    },
                    placeholder = { Text(text = "City") },
                    modifier = Modifier
                        .padding(8.dp).background(Color.White),
                    trailingIcon = {
                        if (schoolErrorView.isCityNameError) {
                            Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colors.error)
                        }
                    },
                    isError = schoolErrorView.isCityNameError
                )
                if (schoolErrorView.isCityNameError) {
                    Text(
                        textAlign = TextAlign.End,
                        text = schoolErrorView.cityError!!,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body1,
                    )
                }
                Spacer(Modifier.width(8.dp))

                Button(
                    onClick = {
                        addSchoolDetailsScreenModel.saveSchool()
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF6F4E37),
                        contentColor = Color.White
                    )
                ) {
                    Text("SAVE", fontSize = 24.sp)
                }
            }
        }
    }

}