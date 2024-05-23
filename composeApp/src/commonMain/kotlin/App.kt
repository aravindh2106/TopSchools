import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import database.SchoolDao
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.schoollist.SchoolListScreen


@OptIn(InternalVoyagerApi::class)
@Composable
@Preview
fun App(schoolDao: SchoolDao) {
    MaterialTheme {
        Navigator(SchoolListScreen(schoolDao))
    }
}

