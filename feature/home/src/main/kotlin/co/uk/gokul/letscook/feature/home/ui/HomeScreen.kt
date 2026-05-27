package co.uk.gokul.letscook.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * The landing screen of the application.
 *
 * @param onNavigateToMeals Callback to navigate to the meals screen.
 */
@Composable
fun HomeScreen(onNavigateToMeals: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Home Screen")
        Button(onClick = onNavigateToMeals) {
            Text(text = "Go to Meals")
        }
    }
}
