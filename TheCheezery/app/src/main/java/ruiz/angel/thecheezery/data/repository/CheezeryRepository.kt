package ruiz.angel.thecheezery.data.repository

import kotlinx.coroutines.flow.Flow
import ruiz.angel.thecheezery.data.database.AppDatabase
import ruiz.angel.thecheezery.data.database.entity.ComboEntity
import ruiz.angel.thecheezery.data.database.entity.ProductComboEntity
import ruiz.angel.thecheezery.data.database.entity.ProductEntity
import ruiz.angel.thecheezery.data.database.relation.ComboWithProducts

class CheezeryRepository(private val database: AppDatabase) {
    private val productDao = database.productDao()
    private val comboDao = database.comboDao()
    private val productComboDao = database.productComboDao()

    // ProductDao
    suspend fun insertProduct(product: ProductEntity): Long {
        return productDao.insertProduct(product)
    }

    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }

    suspend fun getProductById(id: Int): ProductEntity? {
        return productDao.getProductById(id)
    }

    // ComboDao
    suspend fun insertCombo(combo: ComboEntity): Long {
        return comboDao.insertCombo(combo)
    }

    fun getAllCombos(): Flow<List<ComboEntity>> {
        return comboDao.getAllCombos()
    }

    suspend fun getComboById(id: Int): ComboEntity? {
        return comboDao.getComboById(id)
    }

    // ProductComboDao
    suspend fun insertProductCombo(productCombo: ProductComboEntity): Long {
        return productComboDao.insertProductCombo(productCombo)
    }
}