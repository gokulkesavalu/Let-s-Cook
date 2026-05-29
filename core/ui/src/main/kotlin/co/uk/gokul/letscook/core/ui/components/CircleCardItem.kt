package co.uk.gokul.letscook.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uk.gokul.letscook.core.common.util.getFlagEmoji

/**
 * A circular card item component, typically used for ingredients or categories.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param areaName The title text to display below the circle.
 * @param countryName The URL of the image to load in the circle.
 * @param onClick Callback to be invoked when the item is clicked.
 */
@Composable
fun CircleCardItem(
    modifier: Modifier = Modifier,
    areaName: String,
    countryName: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(200.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = CircleShape,
            shadowElevation = 2.dp
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = getFlagEmoji(countryName),
                    fontSize = 130.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Text(
            text = areaName,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}
