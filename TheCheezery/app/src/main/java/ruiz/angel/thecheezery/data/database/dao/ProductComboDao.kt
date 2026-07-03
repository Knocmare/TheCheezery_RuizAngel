package ruiz.angel.thecheezery.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ruiz.angel.thecheezery.data.database.entity.ProductComboEntity

@Dao
interface ProductComboDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCombo(productCombo: ProductComboEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCombos(productCombos: List<ProductComboEntity>)

    @Delete
    suspend fun deleteProductCombo(productCombo: ProductComboEntity)

    // Quita todos los productos de un combo
    @Query("DELETE FROM ProductsCombo WHERE idCombo = :comboId")
    suspend fun deleteProductsFromCombo(comboId: Int)
}
