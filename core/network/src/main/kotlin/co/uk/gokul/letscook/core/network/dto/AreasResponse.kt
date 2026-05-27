package co.uk.gokul.letscook.core.network.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object for the areas response.
 *
 * @property meals List of areas (cuisines).
 */
@Serializable
data class AreasResponse(
    val areas: List<AreaDto>,
)

/**
 * Data transfer object for a single area.
 *
 * @property strArea Name of the area (e.g., "Indian", "Italian").
 * @property strCountry Name of the country (e.g., "India", "Italy").
 */
@Serializable
data class AreaDto(
    val strArea: String,
    val strCountry: String,
)
