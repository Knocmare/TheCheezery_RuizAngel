package ruiz.angel.thecheezery.data.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ruiz.angel.thecheezery.data.database.entity.ComboEntity
import ruiz.angel.thecheezery.data.database.entity.ProductEntity
import ruiz.angel.thecheezery.data.database.entity.ProductComboEntity

data class ComboWithProducts(
    @Embedded
    val combo: ComboEntity,

    @Relation(
        parentColumn = "idCombo",
        entityColumn = "idProduct",
        associateBy = Junction(
            value = ProductComboEntity::class,
            parentColumn = "idCombo",
            entityColumn = "idProduct"
        )
    )
    val products: List<ProductEntity>
)