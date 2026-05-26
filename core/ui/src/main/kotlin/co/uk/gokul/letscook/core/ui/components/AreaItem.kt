package co.uk.gokul.letscook.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uk.gokul.letscook.core.common.util.getFlagEmoji
import co.uk.gokul.letscook.core.ui.theme.LetsCookTheme

/**
 * A card-style item representing a geographic area or cuisine.
 * Displays a large flag emoji as the primary visual element.
 *
 * @param areaName The name of the area (e.g., "Indian").
 * @param countryName The name of the country (e.g., "India").
 * @param onClick Callback to be invoked when the item is clicked.
 * @param modifier The modifier to be applied to the layout.
 */
@Composable
fun AreaItem(
    areaName: String,
    countryName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.8f) // Vertical card aspect ratio
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Flag Section (dominant hero element)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f), // Takes up most of the card
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getFlagEmoji(countryName),
                    fontSize = 80.sp
                )
            }

            // Text Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = areaName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = countryName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AreaItemPreview() {
    LetsCookTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            val sampleAreas = listOf(
                "Afghan" to "Afghanistan",
                "Indian" to "India",
                "Italian" to "Italy",
                "Japanese" to "Japan",
                "American" to "USA",
                "Mexican" to "Mexico"
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(sampleAreas) { (area, country) ->
                    AreaItem(
                        areaName = area,
                        countryName = country,
                        onClick = {}
                    )
                }
            }
        }
    }
}
