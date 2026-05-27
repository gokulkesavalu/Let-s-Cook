package co.uk.gokul.letscook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.uk.gokul.letscook.core.navigation.Screen
import co.uk.gokul.letscook.core.ui.theme.LetsCookTheme
import co.uk.gokul.letscook.feature.home.ui.HomeScreen
import co.uk.gokul.letscook.feature.home.ui.HomeViewModel
import co.uk.gokul.letscook.feature.mealdetails.MealDetailsScreen
import co.uk.gokul.letscook.feature.meals.MealsScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity of the application, responsible for hosting the navigation graph.
 * Annotated with [AndroidEntryPoint] to enable Hilt dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsCookTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Screen.Home) {
                        composable<Screen.Home> {
                            val viewModel = hiltViewModel<HomeViewModel>()
                            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                            HomeScreen(
                                uiState = uiState,
                                onNavigateToMeals = { title, type, value ->
                                    navController.navigate(Screen.Meals(title, type, value))
                                }
                            )
                        }
                        composable<Screen.Meals> {
                            MealsScreen()
                        }
                        composable<Screen.MealDetails> {
                            MealDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}
