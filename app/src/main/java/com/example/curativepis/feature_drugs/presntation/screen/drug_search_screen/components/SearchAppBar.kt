package com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.example.curativepis.R
import com.example.curativepis.ui.theme.babyBlue
import com.example.curativepis.ui.theme.spacing

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    focusRequester: FocusRequester,
    keyboardController: SoftwareKeyboardController?,
    textFieldModifier: Modifier = Modifier,
) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(MaterialTheme.spacing.toolbarHeight),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.primary
    ) {
        TextField(
            modifier = textFieldModifier
                .fillMaxSize(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        style = MaterialTheme.typography.subtitle1.copy(
                            color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f),
                            textAlign = TextAlign.Start
                        ),
                        text = stringResource(id = R.string.search_lable),
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))
                    Icon(imageVector = Icons.Filled.Search, contentDescription =null , tint = MaterialTheme.colors.onPrimary)
                }
            },
            leadingIcon = {
                IconButton(onClick = {
                    keyboardController?.hide()
                    onCloseClicked()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f))
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                textColor = MaterialTheme.colors.onPrimary
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            )

        )

    }
}

