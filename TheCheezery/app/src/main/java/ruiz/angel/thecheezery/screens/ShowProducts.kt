package ruiz.angel.thecheezery.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ruiz.angel.thecheezery.R
import ruiz.angel.thecheezery.domain.Product
import ruiz.angel.thecheezery.viewModel.ProductViewModel

@Composable
fun ShowProducts(viewModel: ProductViewModel, type: String) {
    LaunchedEffect(type) {
        viewModel.getProductsByType(type)
    }

    Column {
        Text("Products: $type")
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(viewModel.productsListState) { product ->
                ProductItem(product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Image(painterResource(R.drawable.muffin), contentDescription = "muffin")
        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            Text("${product.name}")
            Text("${product.description}")
        }
        Text("$${product.price}")
    }
}