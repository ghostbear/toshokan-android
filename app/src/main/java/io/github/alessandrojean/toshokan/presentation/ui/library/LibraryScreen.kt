package io.github.alessandrojean.toshokan.presentation.ui.library

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.alessandrojean.toshokan.R
import io.github.alessandrojean.toshokan.presentation.ui.isbnlookup.IsbnLookupScreen

class LibraryScreen : Screen {

  @Composable
  override fun Content() {
    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
      topBar = {
        SmallTopAppBar(
          modifier = Modifier.statusBarsPadding(),
          title = { Text(stringResource(R.string.library)) },
          actions = {
            IconButton(onClick = { /*TODO*/ }) {
              Icon(
                Icons.Default.Search,
                contentDescription = stringResource(R.string.action_search)
              )
            }
          }
        )
      },
      content = { innerPadding ->
        Text(stringResource(R.string.library), modifier = Modifier.padding(innerPadding))
      },
      floatingActionButtonPosition = FabPosition.End,
      floatingActionButton = {
        FloatingActionButton(
          onClick = {
            navigator.push(IsbnLookupScreen())
          }
        ) {
          Icon(
            Icons.Default.QrCodeScanner,
            contentDescription = stringResource(R.string.action_new_book)
          )
        }
      }
    )
  }

}
