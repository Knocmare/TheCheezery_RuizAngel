package ruiz.angel.thecheezery.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ruiz.angel.thecheezery.R
import ruiz.angel.thecheezery.ui.theme.Brighter_Pink
import ruiz.angel.thecheezery.ui.theme.Dusty_white
import ruiz.angel.thecheezery.ui.theme.Purple_grey

@Composable
fun WelcomeScreen(onStartClick:() -> Unit = {}) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(Modifier.padding(innerPadding).fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                Image(
                    painterResource(R.drawable.the_cheezery),
                    contentDescription = "the cheezery",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth().background(Purple_grey).padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Welcome to The Cheezery",
                    color = Dusty_white,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    "Home of the most wonderful desserts ever seen (and tasted) by the human being.",
                    color = Dusty_white,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Button(
                    content = { Text("Get started!") },
                    onClick = {
                        onStartClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Brighter_Pink,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}