package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.curativepis.R
import com.example.curativepis.core.presentation.screen.main_screen.components.ButtonWithElevation
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.components.ScannerTopAppBar
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.components.TextBetweenDivider
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.view_model.ScannerScreenViewModel
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("PermissionLaunchedDuringComposition", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScannerScreen(
    scaffoldState: ScaffoldState,
    viewModel: ScannerScreenViewModel = hiltViewModel(),
) {

    val scop = rememberCoroutineScope()
    val state = viewModel.state.value
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                uri.let {
                    viewModel.onEvent(ScannerScreenEvent.SelectImageFromGellary(imageUri = it!!))
                }
            })


    // Camera permission state
    // todo remove it from this screen
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )


    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = MaterialTheme.spacing.bottomNavigationBar),
            horizontalAlignment = Alignment.CenterHorizontally
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
                onClick = {/*TODO*/ },
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
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary))
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            IconButton(onClick = {
                // open gallery to select image

                galleryLauncher.launch("image/*")
            }

            ) {
                Icon(painter = painterResource(id = R.drawable.ic_photo_library_24dp),
                    contentDescription = "gallery",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(MaterialTheme.spacing.extraLarge))
            }




            LaunchedEffect(key1 = state.isImagePackedFromGallery) {
                if (state.isImagePackedFromGallery) {
                    val source = state.imageUri?.let {
                        Log.d("Error", it.toString())
                        ImageDecoder
                            .createSource(context.contentResolver, it)
                    }
                    bitmap.value = source?.let { ImageDecoder.decodeBitmap(it) }
                }

            }
            if (state.isImagePackedFromGallery) {
                bitmap.value?.let {
                    Log.d("Error", it.toString())
                    Image(bitmap = it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp))
                }

            }



            ButtonWithElevation(
                startIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                endIcon = null,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.spacing.largeButtonH)
                    .padding(horizontal = 40.dp),
                text = "Result"
            )


        }

    }


}

//fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
//    val bytes = ByteArrayOutputStream()
//    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//    val path =
//        MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
//    return Uri.parse(path)
//}








