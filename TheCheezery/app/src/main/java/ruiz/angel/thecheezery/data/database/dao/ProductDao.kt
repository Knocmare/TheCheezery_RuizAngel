package ruiz.angel.thecheezery.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ruiz.angel.thecheezery.data.database.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query(value = "SELECT * FROM Products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query(value = "SELECT * FROM Products WHERE idProduct = :productId")
    suspend fun getProductById(productId: Int): ProductEntity?

    @Query(value = "SELECT * FROM Products WHERE nameProduct LIKE '%' || :name || '%'")
    fun searchProducts(name: String): Flow<List<ProductEntity>>
}
