package co.uk.gokul.letscook.feature.home.domain.model

/**
 * Domain model representing a list of geographic areas.
 *
 * @property categories List of areas.
 */
data class Areas(val categories: List<Area>)

/**
 * Domain model representing a single geographic area.
 *
 * @property strArea Name of the area.
 * @property strCountry Name of the country.
 */
data class Area(
    val strArea: String,
    val strCountry: String,
)