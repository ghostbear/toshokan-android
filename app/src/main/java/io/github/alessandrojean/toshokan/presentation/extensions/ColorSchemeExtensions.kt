package io.github.alessandrojean.toshokan.presentation.extensions

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import kotlin.math.ln

/**
 * https://github.com/androidx/androidx/blob/androidx-main/compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/ColorScheme.kt#L476-L482
 */
@Composable
fun ColorScheme.surfaceColorAtNavigationBarElevation(): Color {
  val elevation = LocalAbsoluteTonalElevation.current + 3.dp
  if (elevation == 0.dp) {
    return surface
  }

  val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
  return primary.copy(alpha = alpha).compositeOver(surface)
}