package ruiz.angel.thecheezery.data

import android.content.ContentValues
import ruiz.angel.thecheezery.domain.Combo
import ruiz.angel.thecheezery.domain.ProductCombo
import ruiz.angel.thecheezery.domain.Product
import ruiz.angel.thecheezery.data.CheezeryContract.CombosEntry
import ruiz.angel.thecheezery.data.CheezeryContract.ProductsComboEntry
import ruiz.angel.thecheezery.data.CheezeryContract.ProductsEntry

class CombosDAO(private val dbHelper: DatabaseHelper) {

    fun insertCombo(combo: Combo): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(CombosEntry.COLUMN_NAME, combo.name)
            put(CombosEntry.COLUMN_PRICE, combo.price)
        }
        return db.insert(CombosEntry.TABLE_NAME, null, values)
    }

    fun addProductToCombo(comboId: Int, productId: Int): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(ProductsComboEntry.COLUMN_COMBO_ID, comboId)
            put(ProductsComboEntry.COLUMN_PRODUCT_ID, productId)
        }
        return db.insert(ProductsComboEntry.TABLE_NAME, null, values)
    }

    fun getAllCombos(): List<Combo> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            CombosEntry.TABLE_NAME,
            arrayOf(CombosEntry.COLUMN_ID, CombosEntry.COLUMN_NAME, CombosEntry.COLUMN_PRICE),
            null, null, null, null, null
        )
        val combos = mutableListOf<Combo>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(CombosEntry.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(CombosEntry.COLUMN_NAME))
                val price = getFloat(getColumnIndexOrThrow(CombosEntry.COLUMN_PRICE))
                combos.add(Combo(id, name, price))
            }
        }
        cursor.close()
        return combos
    }

    fun getProductsForCombo(comboId: Int): List<Product> {
        val db = dbHelper.readableDatabase
        val query = """
            SELECT p.${ProductsEntry.COLUMN_ID}, p.${ProductsEntry.COLUMN_NAME}, 
                   p.${ProductsEntry.COLUMN_PRICE}, p.${ProductsEntry.COLUMN_IMAGE}, 
                   p.${ProductsEntry.COLUMN_DESCRIPTION}, p.${ProductsEntry.COLUMN_TYPE}
            FROM ${ProductsComboEntry.TABLE_NAME} pc
            INNER JOIN ${ProductsEntry.TABLE_NAME} p 
                ON pc.${ProductsComboEntry.COLUMN_PRODUCT_ID} = p.${ProductsEntry.COLUMN_ID}
            WHERE pc.${ProductsComboEntry.COLUMN_COMBO_ID} = ?
        """.trimIndent()

        val cursor = db.rawQuery(query, arrayOf(comboId.toString()))
        val products = mutableListOf<Product>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(ProductsEntry.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_NAME))
                val price = getFloat(getColumnIndexOrThrow(ProductsEntry.COLUMN_PRICE))
                val image = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_IMAGE))
                val description = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_DESCRIPTION))
                val type = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_TYPE))
                products.add(Product(id, name, price, image, description, type))
            }
        }
        cursor.close()
        return products
    }
}