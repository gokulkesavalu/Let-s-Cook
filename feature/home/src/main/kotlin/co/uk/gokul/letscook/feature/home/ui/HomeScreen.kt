package co.uk.gokul.letscook.feature.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.uk.gokul.letscook.core.ui.R
import co.uk.gokul.letscook.core.ui.components.AreaItem

/**
 * The landing screen of the application.
 *
 * @param onNavigateToMeals Callback to navigate to the meals screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onNavigateToMeals: (String, String, String) -> Unit = { _, _, _ -> }
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
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.showLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            if (uiState.areas.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    items(uiState.areas) { area ->
                        AreaItem(
                            areaName = area.strArea,
                            countryName = area.strCountry,
                            onClick = {},
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}