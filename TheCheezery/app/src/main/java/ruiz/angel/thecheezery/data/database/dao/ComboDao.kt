package ruiz.angel.thecheezery.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ruiz.angel.thecheezery.data.database.entity.ComboEntity

@Dao
interface ComboDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCombo(combo: ComboEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCombos(combos: List<ComboEntity>)

    @Update
    suspend fun updateCombo(combo: ComboEntity)

    @Delete
    suspend fun deleteCombo(combo: ComboEntity)

    @Query(value = "SELECT * FROM Combos")
    fun getAllCombos(): Flow<List<ComboEntity>>

    @Query(value = "SELECT * FROM Combos WHERE idCombo = :comboId")
    suspend fun getComboById(comboId: Int): ComboEntity?

    @Query(value = "SELECT * FROM Combos WHERE nameCombo LIKE '%' || :name || '%'")
    fun searchCombos(name: String): Flow<List<ComboEntity>>
}