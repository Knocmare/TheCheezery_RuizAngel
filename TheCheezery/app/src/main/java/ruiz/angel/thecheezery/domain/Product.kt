package ruiz.angel.thecheezery.domain

data class Product (
    val id: Int = 0,
    val name: String,
    val price: Float,
    val image: String? = null, // podría ser una ruta URI o nombre de archivo
    val description: String? = null,
    val type: String
)

