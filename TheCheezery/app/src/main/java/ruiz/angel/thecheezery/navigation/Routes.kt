package ruiz.angel.thecheezery.navigation

import kotlinx.serialization.Serializable

@Serializable
object Welcome
@Serializable
object Menu
@Serializable
object AddProduct
@Serializable
data class ShowProductsRoute(val type: String)

@Serializable
object AddCombo