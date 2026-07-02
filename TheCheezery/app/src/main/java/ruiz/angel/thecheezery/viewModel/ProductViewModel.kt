package ruiz.angel.thecheezery.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ruiz.angel.thecheezery.data.ProductsDAO
import ruiz.angel.thecheezery.domain.Product
import kotlinx.coroutines.launch

class ProductViewModel (private val dao: ProductsDAO, private val context: Context): ViewModel(){

    var productsListState by mutableStateOf(listOf<Product>())

    init {
        viewModelScope.launch {
            getAllProducts()
        }
    }

    fun saveProduct(product: Product){

        val newProduct = dao.insertProduct(product)
        if (newProduct != -1L){
            Toast.makeText(context, "Producto guardado", Toast.LENGTH_SHORT).show()
            getAllProducts()

        }else{
            Toast.makeText(context, "Hubo un error al guardar", Toast.LENGTH_SHORT).show()
        }

    }

    fun getAllProducts(){
        productsListState =  dao.getAllProducts()
    }

    fun getProductsByType(type: String) {
        productsListState = dao.getProductsByType(type)
    }

}