import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.prasadmhapankar.kmpmuseum.presentation.DetailScreen
import org.prasadmhapankar.kmpmuseum.presentation.ListScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "list") {
            composable("list") {
                ListScreen(
                    navigateToDetails = { objectId ->
                        navController.navigate("details/$objectId")
                    }
                )
            }
            composable(
                "details/{objectId}",
                arguments = listOf(navArgument("objectId") { type = NavType.IntType })
            ) { backstack ->
                backstack.arguments?.getInt("objectId")?.let {
                    DetailScreen(
                        objectId = it,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}