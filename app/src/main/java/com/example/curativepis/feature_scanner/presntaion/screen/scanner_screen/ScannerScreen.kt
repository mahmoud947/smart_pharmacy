package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.curativepis.R
import com.example.curativepis.core.presentation.components.ButtonWithElevation
import com.example.curativepis.core.presentation.components.LoadingView
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.feature_auth.presntation.screen.login_screen.view_model.LoginScreenViewModel
import com.example.curativepis.feature_auth.presntation.util.AuthScreens
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.components.ScannerTopAppBar
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.components.TextBetweenDivider
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.view_model.ScannerScreenViewModel
import com.example.curativepis.feature_scanner.presntaion.util.ScannerScreens
import com.example.curativepis.feature_scanner.util.UriPathHelper
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("PermissionLaunchedDuringComposition", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoilApi::class)
@Composable
fun ScannerScreen(
    scaffoldState: ScaffoldState,
    viewModel: ScannerScreenViewModel = hiltViewModel(),
    navController: NavController,
) {

    val scop = rememberCoroutineScope()
    val state = viewModel.uiState.value
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    val scrollState= rememberScrollState()
    val uriPathHelper=UriPathHelper()

    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                uri.let {imageUre->

                    viewModel.onEvent(ScannerScreenEvent.PickImageFromGellary(imageUri = imageUre))

                }
            })
    // Permission state
    val permissionState = rememberPermissionState(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )



    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ScannerScreenViewModel.ValidationEvent.Success -> {
                    navController.navigate(route = ScannerScreens.ScannerResultScreen.route)
                }
            }
        }
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = MaterialTheme.spacing.bottomNavigationBar)
        .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier
            .align(Alignment.TopCenter)
            .fillMaxWidth()
            .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ScannerTopAppBar(
                onClick = {
                    scop.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = MaterialTheme.spacing.xLarge,
                    vertical = MaterialTheme.spacing.medium),
                contentAlignment = Alignment.Center) {

                Image(modifier = Modifier.padding(start = MaterialTheme.spacing.regulator),
                    painter = painterResource(id = R.drawable.ic_undraw_upload_re_pasx),
                    contentDescription = "folder image")
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            ButtonWithElevation(
                startIcon = painterResource(id = R.drawable.ic_outline_camera_alt_24),
                endIcon = null,
                onClick = {
                    navController.navigate(ScannerScreens.CameraScreen.route)
                },
                modifier = Modifier
                    .width(MaterialTheme.spacing.smallButtonX)
                    .height(MaterialTheme.spacing.smallButtonH)
                    .padding(horizontal = 40.dp),
                text = "Use Camera"
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            TextBetweenDivider(text = "Or")
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Text(text = "Select the documents from gallery",
                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primaryVariant))
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            IconButton(onClick = {
                // open gallery to select image
                galleryLauncher.launch("image/*")
            }

            ) {
                Icon(painter = painterResource(id = R.drawable.ic_photo_library_24dp),
                    contentDescription = "gallery",
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
                    modifier = Modifier.size(MaterialTheme.spacing.extraLarge))
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))


            Box(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
                contentAlignment = Alignment.Center) {
                    state.imageUri?.let { uri ->
                        Image(
                            painter = rememberImagePainter(request = ImageRequest.Builder(context = context)
                                .data(uri)
                                .build()
                            ),
                            contentDescription =null,
                            modifier = Modifier.matchParentSize()
                        )
                    }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
                contentAlignment = Alignment.Center){
                when {
                    state.isLoding -> {
                        LoadingView(modifier = Modifier.matchParentSize())
                    }
                }

            }
        }

        ButtonWithElevation(
            startIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
            endIcon = null,
            onClick = {
                permissionState.launchPermissionRequest()
                val filePath= state.imageUri?.let { uriPathHelper.getPath(context = context, it) }
                viewModel.onEvent(ScannerScreenEvent.UploadImage(filePath = filePath.toString()))
                if (state.scannerresponse!=null){
                    Log.d("drugsResp",state.scannerresponse.toString())
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MaterialTheme.spacing.medium,
                    start = MaterialTheme.spacing.regulator,
                    end = MaterialTheme.spacing.regulator)
                .fillMaxWidth()
                .height(MaterialTheme.spacing.largeButtonH),
            text = "Result",
            isEnable = state.isButtonEnable
        )


    }

}

fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path =
        MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
    return Uri.parse(path)
}









