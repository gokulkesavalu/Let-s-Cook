package co.uk.gokul.letscook.core.network.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object for the response containing a list of meals.
 *
 * @property meals The list of meals returned by the API.
 */
@Serializable
data class MealsResponse(
    val meals: List<Meal>
)