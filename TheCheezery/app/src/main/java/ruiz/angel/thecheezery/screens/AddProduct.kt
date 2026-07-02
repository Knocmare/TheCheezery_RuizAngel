package ruiz.angel.thecheezery.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import ruiz.angel.thecheezery.R
import ruiz.angel.thecheezery.domain.Product
import ruiz.angel.thecheezery.ui.theme.Pinky
import ruiz.angel.thecheezery.viewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(innerPadding: PaddingValues, viewModel: ProductViewModel = viewModel()) {

    var name by remember { mutableStateOf(value = "") }
    var priceField by remember { mutableStateOf(value = "") }
    var description by remember { mutableStateOf(value = "") }
    val productTypes = listOf("Hot drinks", "Cold drinks", "Salties", "Sweets")
    var selectedType by remember { mutableStateOf(productTypes[0]) }
    var expanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(innerPadding)) {
        Text("Add a new product", color = Pinky, fontSize = 30.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(Modifier.height(height = 30.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = {Text(text = "Name")},
        )
        OutlinedTextField(
            value = priceField,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {priceField = it},
            label = {Text(text = "Price")},
            trailingIcon = { Image(
                painter = painterResource(id = R.drawable.dolar),
                contentDescription = "Dolar icon",
            ) }
        )

        OutlinedTextField(
            value = description,
            onValueChange = {description = it},
            label = {Text(text = "Description")},
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedType,
                onValueChange = {},
                readOnly = true,
                label = { Text("Type") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                productTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            selectedType = type
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(15.dp))

        ImagePicker()

        Button(
            content = { Text("Save product") },
            onClick = {viewModel.saveProduct(
                Product(
                    name = name,
                    price = priceField.toFloatOrNull() ?: 0f,
                    image = "",
                    description = description,
                    type = selectedType
                )
            )}
        )
    }
}

@Composable
fun ImagePicker() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column {
        Button(onClick = {
            launcher.launch("image/*")
        }) {
            Text("Select image")
        }

        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
    }
}