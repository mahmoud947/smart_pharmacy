package com.example.curativepis.feature_ath.presntation.screen.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.curativepis.R
import com.example.curativepis.ui.theme.spacing

@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    val scrollState= rememberScrollState()
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colors.primaryVariant)) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                    Text(text = stringResource(id = R.string.app_label), style = MaterialTheme.typography.h3.copy(MaterialTheme.colors.onPrimary))
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
                    Text(text = stringResource(id = R.string.sub_label), style = MaterialTheme.typography.subtitle1.copy(MaterialTheme.colors.onPrimary))
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                }
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant)){
                Box(modifier = Modifier.matchParentSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.login_screen_on_background),
                        contentDescription = null,
                        modifier = Modifier
                            .matchParentSize(),
                        contentScale = ContentScale.FillHeight
                    )
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.app_logo),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                    }
                }
            }

        }

    }
}


