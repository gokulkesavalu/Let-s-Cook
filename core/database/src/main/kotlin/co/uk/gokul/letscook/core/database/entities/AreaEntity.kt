package co.uk.gokul.letscook.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a geographical area or cuisine.
 *
 * @property strArea Name of the area (e.g., "British", "Chinese").
 * @property strCountry Country associated with the area.
 * @property cachedAt The timestamp when this entity was cached in the database.
 */
@Entity(tableName = "areas")
data class AreaEntity(
    @PrimaryKey val strArea: String,
    val strCountry: String,
    val cachedAt: Long = System.currentTimeMillis()
)