package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.curativepis.ui.theme.spacing
import kotlin.math.roundToInt
/*
 val toolbarHeight = 48.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx
 */
@Composable
fun CustomTopAppBar(
    toolbarHeight: Dp,
    toolbarOffsetHeightPx:Float,
    menuIconOnClick:()->Unit,
    searchIconOnClick:()->Unit
) {
    TopAppBar(
        modifier = androidx.compose.ui.Modifier
            .height(toolbarHeight)
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.roundToInt()) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        title = { Text("Drugs") },
        actions = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(shape = MaterialTheme.shapes.large)
                .clickable {

                }
                .background(MaterialTheme.colors.primaryVariant),
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier.fillMaxWidth().clickable {
                    searchIconOnClick()
                }) {
                    Text(
                        text = "Search .....",
                        color = MaterialTheme.colors.background.copy(alpha = 0.8f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = MaterialTheme.spacing.large,
                                top = MaterialTheme.spacing.small,
                                bottom = MaterialTheme.spacing.small)
                            .weight(4f),
                        textAlign = TextAlign.Start
                    )
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon", modifier = Modifier.weight(1f) )
                }

            }
        },

        elevation = MaterialTheme.spacing.regulator,
        navigationIcon = {
            IconButton(
                onClick = {
                    menuIconOnClick()
                },
            ) {
                Icon(imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu Icon")

            }
        }

    )
}