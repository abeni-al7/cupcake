package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = {}
            )
        }
        composeTestRule.onNodeWithStringId(R.string.order_cupcakes).assertIsDisplayed()
        DataSource.quantityOptions.forEach { item ->
            composeTestRule.onNodeWithText(composeTestRule.activity.getString(item.first))
                .assertIsDisplayed()
        }
    }

    @Test
    fun summaryScreen_verifyContent() {
        val uiState = OrderUiState(
            quantity = 3,
            flavor = "Vanilla",
            date = "Wed Jul 21",
            price = "$100"
        )
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = uiState,
                onCancelButtonClicked = { },
                onSendButtonClicked = { subject: String, summary: String -> },
            )
        }
        composeTestRule.onNodeWithText(uiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText("3 cupcakes").assertIsDisplayed()
    }

    @Test
    fun selectOptionScreen_onValueChosen_enablesNextButton() {
        composeTestRule.setContent {
            SelectOptionScreen(
                subtotal = "20",
                options = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
                onSelectionChanged = {},
                onCancelButtonClicked = {},
                onNextButtonClicked = {}
            )
        }
        composeTestRule.onNodeWithText("Option 1").performClick()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}