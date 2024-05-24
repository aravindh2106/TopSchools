package screens.addschooldetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

data object AddSchoolDetailsScreen : Screen {
    @Composable
    override fun Content() {
        AddSchoolDetails()
    }
}

@Composable
fun AddSchoolDetails() {
    val textState1 = remember { mutableStateOf("") }
    val textState2 = remember { mutableStateOf("") }
    val textState3 = remember { mutableStateOf("") }
    val offset = Offset(5.0f, 10.0f)
    Column(
        Modifier
            .fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "Fill the school details",
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = Color.Blue,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Gray, offset = offset, blurRadius = 3f
                )
            )
        )

        OutlinedTextField(
            value = textState1.value,
            onValueChange = { textState1.value = it },
            placeholder = { Text(text = "School name") },
            modifier = Modifier
                .padding(8.dp)
        )
        Spacer(Modifier.width(8.dp))
        OutlinedTextField(
            value = textState2.value,
            onValueChange = { textState2.value = it },
            placeholder = { Text(text = "Description") },
            modifier = Modifier
                .padding(8.dp)
        )
        Spacer(Modifier.width(8.dp))
        OutlinedTextField(
            value = textState3.value,
            onValueChange = { textState3.value = it },
            placeholder = { Text(text = "City") },
            modifier = Modifier
                .padding(8.dp)
        )
        Spacer(Modifier.width(8.dp))

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFe8f5c4), // Set the background color
                contentColor = Color.Black   // Set the text color
            )
        ) {
            Text("SAVE",fontWeight = FontWeight.Bold, fontSize = 24.sp,)
        }
    }
}