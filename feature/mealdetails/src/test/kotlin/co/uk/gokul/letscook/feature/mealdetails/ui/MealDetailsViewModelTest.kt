package co.uk.gokul.letscook.feature.mealdetails.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import co.uk.gokul.letscook.feature.mealdetails.domain.model.MealDetails
import co.uk.gokul.letscook.feature.mealdetails.domain.repo.MealDetailsRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MealDetailsViewModelTest {

    private val mealDetailsRepository: MealDetailsRepository = mockk()

    private val mealId = "53281"

    private val savedStateHandle: SavedStateHandle = SavedStateHandle(mapOf("idMeal" to mealId))

    private lateinit var viewModel: MealDetailsViewModel
    
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when initialized, getMealsById should be called and uiState should emit Loading then Success`() =
        runTest {
            val mealDetails = mockk<MealDetails>()
            coEvery { mealDetailsRepository.getMealsById(mealId) } returns Result.success(
                mealDetails,
            )
            viewModel = MealDetailsViewModel(mealDetailsRepository, savedStateHandle)
            viewModel.uiState.test {
                assertThat(awaitItem()).isEqualTo(MealDetailsUiState.Loading)
                assertThat(awaitItem()).isEqualTo(MealDetailsUiState.Success(mealDetails = mealDetails))
                coVerify(exactly = 1) { mealDetailsRepository.getMealsById(mealId) }
                expectNoEvents()
            }
        }
}
