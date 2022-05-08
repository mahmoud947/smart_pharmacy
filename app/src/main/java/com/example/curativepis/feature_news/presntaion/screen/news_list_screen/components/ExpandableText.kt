package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.curativepis.ui.theme.spacing

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    minimizedMaxLines: Int,
    alignment: Alignment= Alignment.BottomStart,
    textStyle: TextStyle=MaterialTheme.typography.body1
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val textLayoutResultState = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }
    var isClickable by remember {
        mutableStateOf(false)
    }
    var finalText by remember {
        mutableStateOf(text)
    }
    val textLayoutResult = textLayoutResultState.value


    LaunchedEffect(
        textLayoutResult
    ) {
        if (textLayoutResult == null)
            return@LaunchedEffect
        when {
            isExpanded -> {
                finalText = "$text Show Less"
            }
            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(minimizedMaxLines - 1)
                val showMoreString = "...Show More"
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile {
                        it == ' ' || it == '.'
                    }
                finalText = "$adjustedText$showMoreString"
                isClickable = true
            }
        }
    }
    Box(
        modifier = modifier.fillMaxSize()
            .clickable(enabled = isClickable) {
                isExpanded = !isExpanded
            }
            .animateContentSize(),
        contentAlignment = alignment
    ) {
        Text(
            text = finalText,
            style = textStyle.copy(color = MaterialTheme.colors.onSurface),
            maxLines = if (isExpanded) Int.MAX_VALUE else minimizedMaxLines,
            onTextLayout = {
                textLayoutResultState.value = it
            },
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        )

    }

}





























