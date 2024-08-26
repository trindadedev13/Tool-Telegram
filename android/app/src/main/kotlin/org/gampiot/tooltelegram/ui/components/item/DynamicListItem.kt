package org.gampiot.tooltelegram.ui.components.item

// from VegaBobo/DSU-Sideloader

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

import org.gampiot.tooltelegram.ui.components.cards.CardBox

@Composable
fun DynamicListItem(
    listLength: Int,
    currentValue: Int,
    content: @Composable () -> Unit,
) {
    val shape = when (currentValue) {
        0 -> RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
        listLength -> RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
        else -> RoundedCornerShape(0.dp)
    }
    CardBox(
        addPadding = false,
        roundedCornerShape = shape,
    ) {
        content()
    }
}
