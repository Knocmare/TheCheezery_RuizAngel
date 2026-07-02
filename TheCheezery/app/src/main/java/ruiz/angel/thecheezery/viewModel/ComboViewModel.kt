package ruiz.angel.thecheezery.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ruiz.angel.thecheezery.data.CombosDAO
import ruiz.angel.thecheezery.domain.Combo

class ComboViewModel(private val dao: CombosDAO, private val context: Context) : ViewModel() {

    var combosListState by mutableStateOf(listOf<Combo>())

    fun saveCombo(name: String, price: Float, productIds: List<Int>) {
        val comboId = dao.insertCombo(Combo(name = name, price = price))
        if (comboId != -1L) {
            productIds.forEach { productId ->
                dao.addProductToCombo(comboId.toInt(), productId)
            }
            Toast.makeText(context, "Combo guardado", Toast.LENGTH_SHORT).show()
            getAllCombos()
        } else {
            Toast.makeText(context, "Hubo un error al guardar el combo", Toast.LENGTH_SHORT).show()
        }
    }

    fun getAllCombos() {
        combosListState = dao.getAllCombos()
    }
}