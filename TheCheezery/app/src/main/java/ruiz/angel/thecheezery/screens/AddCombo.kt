package ruiz.angel.thecheezery.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ruiz.angel.thecheezery.domain.Product
import ruiz.angel.thecheezery.ui.theme.Pinky
import ruiz.angel.thecheezery.viewModel.ComboViewModel

@Composable
fun AddComboScreen(productsAvailable: List<Product>, viewModel: ComboViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var priceField by remember { mutableStateOf("") }
    val selectedProducts = remember { mutableStateListOf<Product>() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text("Add a new combo", color = Pinky, fontSize = 30.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Combo name") })
        OutlinedTextField(value = priceField, onValueChange = { priceField = it }, label = { Text("Price") })

        Spacer(Modifier.height(16.dp))
        Text("Select products for this combo:")

        LazyColumn(modifier = Modifier.height(250.dp)) {
            items(productsAvailable) { product ->
                val isChecked = selectedProducts.contains(product)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { checked ->
                            if (checked) selectedProducts.add(product)
                            else selectedProducts.remove(product)
                        }
                    )
                    Text("${product.name} - $${product.price}")
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            viewModel.saveCombo(
                name = name,
                price = priceField.toFloatOrNull() ?: 0f,
                productIds = selectedProducts.map { it.id }
            )
        }) {
            Text("Save combo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddComboPreview() {
    //AddComboScreen()
}