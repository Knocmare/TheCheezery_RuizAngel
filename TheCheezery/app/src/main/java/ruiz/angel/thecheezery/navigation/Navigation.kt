package ruiz.angel.thecheezery.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ruiz.angel.thecheezery.screens.AddComboScreen
import ruiz.angel.thecheezery.screens.AddProductScreen
import ruiz.angel.thecheezery.screens.MenuScreen
import ruiz.angel.thecheezery.screens.ShowProducts
import ruiz.angel.thecheezery.screens.WelcomeScreen
import ruiz.angel.thecheezery.viewModel.ComboViewModel
import ruiz.angel.thecheezery.viewModel.ProductViewModel

@Composable
fun Navigation(innerPadding: PaddingValues, productViewModel: ProductViewModel = viewModel(), comboViewModel: ComboViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Welcome,
        Modifier.padding(paddingValues = innerPadding)
    ) {
        composable<Welcome> {
            WelcomeScreen(onStartClick = { navController.navigate(route = Menu) })
        }
        composable<Menu> {
            MenuScreen(
                onCategoryClick = { type -> navController.navigate(route = ShowProductsRoute(type)) },
                onAddProductClick = { navController.navigate(route = AddProduct) },
                onCombosClick = { navController.navigate(AddCombo) }
            )
        }
        composable<ShowProductsRoute> { backStackEntry ->
            val route: ShowProductsRoute = backStackEntry.toRoute()
            ShowProducts(productViewModel, route.type)
        }
        composable<AddProduct> {
            AddProductScreen(innerPadding, productViewModel)
        }
        composable<AddCombo> {
            AddComboScreen(productViewModel.productsListState, comboViewModel)
        }
    }
}