package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.curativepis.ui.theme.spacing

@Composable
fun ScannerTopAppBar(
    navigationIcon: ImageVector = Icons.Filled.Menu,
    onClick: () -> Unit,
) {
    androidx.compose.material.TopAppBar(
        modifier = Modifier
            .height(MaterialTheme.spacing.toolbarHeight),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        title = { Text("Scanner") },
        elevation = MaterialTheme.spacing.regulator,
        navigationIcon = {
            IconButton(
                onClick = {
                    onClick()
                },
            ) {
                Icon(imageVector = navigationIcon,
                    contentDescription = "back Icon")

            }
        }

    )

}