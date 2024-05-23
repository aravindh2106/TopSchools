package screens.schoollist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import database.School
import database.SchoolDao
import screens.schooldetails.SchoolDetailsScreen


data class SchoolListScreen(val schoolDao: SchoolDao) : Screen {
    @Composable
    override fun Content() {
        // Create an instance of SchoolListScreenModel
        val schoolListScreenModel = remember { SchoolListScreenModel(schoolDao) }
        val navigator = LocalNavigator.currentOrThrow
        SchoolList(
            schoolDao = schoolDao,
            screenModel = schoolListScreenModel,
            onItemClick = {
                navigator.push(SchoolDetailsScreen)
            }
        )
    }
}

@Composable
fun SchoolList(
    schoolDao: SchoolDao,
    screenModel: SchoolListScreenModel,
    onItemClick: () -> Unit
) {
    val offset = Offset(5.0f, 10.0f)
    val schoolList by screenModel.schools.collectAsState()
    LaunchedEffect(true) {
        val schools = listOf(
            School(
                schoolName = "Ryan International School",
                city = "Chennai",
                description = ""
            ),
            School(
                schoolName = "Air Force School",
                city = "Thanjavur",
                description = ""
            ),
            School(
                schoolName = "Orchids The International School",
                city = "Bangalore",
                description = ""
            ),
            School(
                schoolName = "Orchids The International School",
                city = "Hydrabad",
                description = ""
            ),
            School(
                schoolName = "National Public School",
                city = "Pune",
                description = ""
            ),
            School(
                schoolName = "Maharishi Vidya Mandir",
                city = "Mumbai",
                description = ""
            ), School(
                schoolName = "Chinmaya Vidyalaya, Anna Nagar",
                city = "Kolkata",
                description = ""
            )
        )
        schools.forEach {
            schoolDao.upsert(it)
        }
    }
    Column(
        Modifier.fillMaxWidth()
            .background(Color(0xFFe8f5c4)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Top best schools",
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = Color.Blue,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Gray, offset = offset, blurRadius = 3f
                )
            )
        )

        NameCardList(schools = schoolList, onClick = { onItemClick() })
    }
}

@Composable
fun CardItem(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).clickable {
                onClick()
            },
        elevation = 4.dp
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun NameCardList(schools: List<School>, onClick: () -> Unit) {
    LazyColumn {
        items(schools) { school ->
            CardItem(name = school.schoolName, onClick = { onClick() })
        }
    }
}