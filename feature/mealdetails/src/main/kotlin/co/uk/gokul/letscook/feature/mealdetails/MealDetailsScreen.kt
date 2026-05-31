package co.uk.gokul.letscook.feature.mealdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Screen that displays detailed information about a specific meal.
 *
 * @param mealId The ID of the meal to display.
 */
@Composable
fun MealDetailsScreen(mealId: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Meal Details Screen for $mealId")
    }
}
