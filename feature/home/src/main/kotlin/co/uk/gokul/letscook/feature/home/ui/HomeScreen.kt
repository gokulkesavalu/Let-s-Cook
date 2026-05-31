package co.uk.gokul.letscook.feature.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import co.uk.gokul.letscook.core.ui.R
import co.uk.gokul.letscook.core.ui.components.CardItem
import co.uk.gokul.letscook.core.ui.components.CircleCardItem
import co.uk.gokul.letscook.feature.home.domain.model.Area
import co.uk.gokul.letscook.feature.home.domain.model.Category
import co.uk.gokul.letscook.feature.home.domain.model.Ingredient

/**
 * The landing screen of the application.
 *
 * @param onNavigateToMeals Callback to navigate to the meals screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onNavigateToMeals: (
        title: String,
        filterType: String,
        filterValue: String
    ) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .height(100.dp)
                            .padding(vertical = 4.dp),
                        contentScale = ContentScale.Fit
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                if (uiState.categories.isNotEmpty()) {
                    item {
                        CategoriesSection(
                            categories = uiState.categories,
                            onCategoryClick = { category ->
                                onNavigateToMeals(category.strCategory, "c", category.strCategory)
                            }
                        )
                    }
                }
                if (uiState.areas.isNotEmpty()) {
                    item {
                        AreasSection(
                            areas = uiState.areas,
                            onAreaClick = { area ->
                                onNavigateToMeals(area.strCountry, "a", area.strCountry)
                            }
                        )
                    }
                }

                if (uiState.ingredients.isNotEmpty()) {
                    item {
                        IngredientsSection(
                            ingredients = uiState.ingredients,
                            onIngredientClick = { ingredient ->
                                onNavigateToMeals(
                                    ingredient.strIngredient,
                                    "i",
                                    ingredient.strIngredient
                                )
                            }
                        )
                    }
                }
            }
            if (uiState.showLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

/**
 * Displays a horizontal row of ingredients.
 */
@Composable
private fun IngredientsSection(
    ingredients: List<Ingredient>,
    onIngredientClick: (Ingredient) -> Unit,
) {
    Text(
        text = "Ingredients",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(10.dp))
    LazyRow(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {
        items(items = ingredients, key = { it.idIngredient }) { ingredient ->
            CardItem(
                title = ingredient.strIngredient,
                description = ingredient.strDescription,
                modifier = Modifier.padding(10.dp),
                onClick = { onIngredientClick(ingredient) }
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

/**
 * Displays a horizontal row of cuisines/areas.
 */
@Composable
private fun AreasSection(
    areas: List<Area>,
    onAreaClick: (Area) -> Unit,
) {
    Text(
        text = "Cuisines",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(10.dp))
    LazyRow(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {
        items(items = areas, key = { it.strArea }) { area ->
            CircleCardItem(
                areaName = area.strArea,
                countryName = area.strCountry,
                onClick = { onAreaClick(area) },
                modifier = Modifier.padding(10.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

/**
 * Displays a horizontal row of meal categories.
 */
@Composable
private fun CategoriesSection(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
) {
    Text(
        text = "Categories",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(10.dp))
    LazyRow(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {
        items(items = categories, key = { it.idCategory }) { category ->
            CardItem(
                title = category.strCategory,
                description = category.strCategoryDescription,
                imageUrl = category.strCategoryThumb,
                modifier = Modifier.padding(10.dp),
                onClick = { onCategoryClick(category) }
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}