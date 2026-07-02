package ruiz.angel.thecheezery.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ruiz.angel.thecheezery.R
import ruiz.angel.thecheezery.ui.theme.Brighter_Pink
import ruiz.angel.thecheezery.ui.theme.Less_Purple
import ruiz.angel.thecheezery.ui.theme.Pinky
import ruiz.angel.thecheezery.ui.theme.Very_purple

val firstGradient = Brush.verticalGradient(listOf(Brighter_Pink, Pinky))
val secondGradient = Brush.verticalGradient(listOf(Pinky, Less_Purple))
val thirdGradient = Brush.verticalGradient(listOf(Less_Purple, Very_purple))

@Composable
fun MenuScreen(onCategoryClick: (String) -> Unit = {}, onAddProductClick: () -> Unit = {}, onCombosClick: () -> Unit = {}) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(R.drawable.grupo2),
                    contentDescription = "the cheezery menu"
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column(modifier = Modifier.weight(1f)){
                        GradientButton("Hot drinks", firstGradient,  Modifier.fillMaxWidth(), onClick = { onCategoryClick("Hot drinks") })
                        GradientButton("Salties", secondGradient,  Modifier.fillMaxWidth(), onClick = { onCategoryClick("Salties") })
                        GradientButton("Combos", thirdGradient,  Modifier.fillMaxWidth(), onClick = onCombosClick)
                    }
                    Column(modifier = Modifier.weight(1f)){
                        GradientButton(text = "Cold drinks", firstGradient,  Modifier.fillMaxWidth(), onClick = { onCategoryClick("Cold drinks") })
                        GradientButton(text = "Sweets", secondGradient,  Modifier.fillMaxWidth(), onClick = { onCategoryClick("Sweets") })
                        GradientButton(text = "Add new product", thirdGradient,  Modifier.fillMaxWidth(), onClick = onAddProductClick)
                    }
                }
            }
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    gradient: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(gradient)
            .clickable { onClick() }
            .padding(vertical = 24.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen()
}