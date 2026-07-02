package ruiz.angel.thecheezery.domain

data class ProductCombo (
    val id: Int = 0,
    //solo guardamos las FK
    val productId: Int,
    val comboId: Int
)
