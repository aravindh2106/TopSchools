package screens.schoollist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
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
import org.jetbrains.compose.resources.painterResource
import screens.addschooldetails.AddSchoolDetailsScreen
import topschools.composeapp.generated.resources.Res
import topschools.composeapp.generated.resources.undraw_empty_re_opql

data class SchoolListScreen(val schoolDao: SchoolDao) : Screen {
    @Composable
    override fun Content() {
        val schoolListScreenModel = remember { SchoolListScreenModel(schoolDao) }
        val navigator = LocalNavigator.currentOrThrow
        SchoolList(
            screenModel = schoolListScreenModel,
            onItemClick = {
                navigator.push(AddSchoolDetailsScreen(schoolDao,it))
            },
            onFloatingActionClick = {
                navigator.push(AddSchoolDetailsScreen(schoolDao))
            }
        )
    }
}

@Composable
fun SchoolList(
    screenModel: SchoolListScreenModel,
    onItemClick: (Int) -> Unit,
    onFloatingActionClick: () -> Unit
) {
    val offset = Offset(5.0f, 10.0f)
    val schoolList by screenModel.schools.collectAsState()

    Box(
        Modifier.fillMaxSize().background(Color(0xFFFED8B1))
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.List,
                "Back",
                Modifier.size(40.dp).padding(top = 8.dp, end = 8.dp)
            )
        }
        Column(
            Modifier
                .fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Top best schools",
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = 24.sp,
                    shadow = Shadow(
                        color = Color(0xFF838481), offset = offset, blurRadius = 3f
                    )
                )
            )
            if (schoolList.isNullOrEmpty()) {
                NoDataCard()
            } else {
                NameCardList(schools = schoolList, onClick = {
                    onItemClick(it)
                })
            }
        }
        FloatingActionButton(
            onClick = { onFloatingActionClick() },
            backgroundColor = Color(0xFF6F4E37),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun CardItem(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
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
fun NoDataCard() {

    Box(
        Modifier
            .fillMaxSize()
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp,
                bottomEnd = 8.dp,
                bottomStart = 8.dp
            ),
            modifier = Modifier.wrapContentSize()
                .padding(8.dp).align(Alignment.Center),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .wrapContentSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(Res.drawable.undraw_empty_re_opql),
                    contentDescription = "",
                    modifier = Modifier.size(128.dp), // adjust size as needed
                    contentScale = ContentScale.Crop // adjust scaling as needed
                )
                Spacer(Modifier.padding(5.dp))
                Text(
                    textAlign = TextAlign.Center,
                    text = "No schools found",
                    style = MaterialTheme.typography.h6,
                )
            }
        }
    }
}

@Composable
fun NameCardList(schools: List<School>, onClick: (Int) -> Unit) {
    LazyColumn {
        items(schools) { school ->
            CardItem(name = school.schoolName, onClick = { onClick(school.id) })
        }
    }
}
