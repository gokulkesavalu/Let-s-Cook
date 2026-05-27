package co.uk.gokul.letscook.feature.home.data.mapper

import co.uk.gokul.letscook.core.database.entities.AreaEntity
import co.uk.gokul.letscook.core.database.entities.CategoryEntity
import co.uk.gokul.letscook.core.database.entities.IngredientEntity
import co.uk.gokul.letscook.core.network.dto.AreaDto
import co.uk.gokul.letscook.core.network.dto.AreasResponse
import co.uk.gokul.letscook.core.network.dto.CategoriesResponse
import co.uk.gokul.letscook.core.network.dto.CategoryDto
import co.uk.gokul.letscook.core.network.dto.IngredientDto
import co.uk.gokul.letscook.core.network.dto.IngredientsResponse
import co.uk.gokul.letscook.feature.home.domain.model.Area
import co.uk.gokul.letscook.feature.home.domain.model.Areas
import co.uk.gokul.letscook.feature.home.domain.model.Categories
import co.uk.gokul.letscook.feature.home.domain.model.Category
import co.uk.gokul.letscook.feature.home.domain.model.Ingredient
import co.uk.gokul.letscook.feature.home.domain.model.Ingredients

/**
 * Maps [CategoriesResponse] DTO to [Categories] domain model.
 */
fun CategoriesResponse.toDomain() = Categories(
    categories = this.categories.map { it.toDomain() }
)

/**
 * Maps [CategoryDto] DTO to [Category] domain model.
 */
fun CategoryDto.toDomain() = Category(
    idCategory = this.idCategory,
    strCategory = this.strCategory,
    strCategoryThumb = this.strCategoryThumb,
    strCategoryDescription = this.strCategoryDescription
)

/**
 * Maps [CategoryDto] DTO to [CategoryEntity] database entity.
 */
fun CategoryDto.toEntity() = CategoryEntity(
    idCategory = this.idCategory,
    strCategory = this.strCategory,
    strCategoryThumb = this.strCategoryThumb,
    strCategoryDescription = this.strCategoryDescription
)

/**
 * Maps [CategoryEntity] database entity to [Category] domain model.
 */
fun CategoryEntity.toDomain() = Category(
    idCategory = this.idCategory,
    strCategory = this.strCategory,
    strCategoryThumb = this.strCategoryThumb,
    strCategoryDescription = this.strCategoryDescription
)

/**
 * Maps [AreasResponse] DTO to [Areas] domain model.
 */
fun AreasResponse.toDomain() = Areas(
    areas = this.areas.map { it.toDomain() }
)

/**
 * Maps [AreaDto] DTO to [Area] domain model.
 */
fun AreaDto.toDomain() = Area(
    strArea = this.strArea,
    strCountry = this.strCountry
)

/**
 * Maps [AreaDto] DTO to [AreaEntity] database entity.
 */
fun AreaDto.toEntity() = AreaEntity(
    strArea = this.strArea,
    strCountry = this.strCountry
)

/**
 * Maps [AreaEntity] database entity to [Area] domain model.
 */
fun AreaEntity.toDomain() = Area(
    strArea = this.strArea,
    strCountry = this.strCountry
)

/**
 * Maps [IngredientsResponse] DTO to [Ingredients] domain model.
 */
fun IngredientsResponse.toDomain() = Ingredients(
    ingredients = this.ingredients.map { it.toDomain() }
)

/**
 * Maps [IngredientDto] DTO to [Ingredient] domain model.
 */
fun IngredientDto.toDomain() = Ingredient(
    idIngredient = this.idIngredient,
    strIngredient = this.strIngredient,
    strDescription = this.strDescription,
    strType = this.strType
)

/**
 * Maps [IngredientDto] DTO to [IngredientEntity] database entity.
 */
fun IngredientDto.toEntity() = IngredientEntity(
    idIngredient = this.idIngredient,
    strIngredient = this.strIngredient,
    strDescription = this.strDescription,
    strType = this.strType
)

/**
 * Maps [IngredientEntity] database entity to [Ingredient] domain model.
 */
fun IngredientEntity.toDomain() = Ingredient(
    idIngredient = this.idIngredient,
    strIngredient = this.strIngredient,
    strDescription = this.strDescription,
    strType = this.strType
)
