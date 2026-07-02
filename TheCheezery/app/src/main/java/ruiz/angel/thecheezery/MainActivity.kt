package ruiz.angel.thecheezery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ruiz.angel.thecheezery.data.CombosDAO
import ruiz.angel.thecheezery.data.DatabaseHelper
import ruiz.angel.thecheezery.data.ProductsDAO
import ruiz.angel.thecheezery.navigation.Navigation
import ruiz.angel.thecheezery.ui.theme.TheCheezeryTheme
import ruiz.angel.thecheezery.viewModel.ComboViewModel
import ruiz.angel.thecheezery.viewModel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val helper = DatabaseHelper(context = this)
        val productsDAO = ProductsDAO(helper)
        val viewModelProducts = ProductViewModel(productsDAO, this)

        val combosDAO = CombosDAO(helper)
        val viewModelCombos = ComboViewModel(combosDAO, this)

        enableEdgeToEdge()
        setContent {
            TheCheezeryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(innerPadding, viewModelProducts, viewModelCombos)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheCheezeryTheme {
        Greeting("Android")
    }
}