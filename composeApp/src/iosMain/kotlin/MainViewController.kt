import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    val dao = remember {
        getSchoolDatabase().schoolDao()
    }
    App(dao)
}