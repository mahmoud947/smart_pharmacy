package com.example.curativepis.feature_scanner.presntaion.screen.camera_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.curativepis.R
import com.example.curativepis.feature_scanner.presntaion.screen.camera_screen.CameraUIAction

@Composable
fun CameraControls(cameraUIAction: (CameraUIAction) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CameraControl(
            R.drawable.ic_baseline_cancel_24 ,
            R.string.icn_camera_view_switch_camera_content_description,
            modifier= Modifier.size(64.dp),
            onClick = { cameraUIAction(CameraUIAction.OnCancleClick) }
        )

        CameraControl(
            R.drawable.ic_baseline_circle_24,
            R.string.icn_camera_view_camera_shutter_content_description,
            modifier= Modifier
                .size(64.dp)
                .padding(1.dp)
                .border(1.dp, Color.White, CircleShape),
            onClick = { cameraUIAction(CameraUIAction.OnCameraClick) }
        )


    }
}


@Composable
fun CameraControl(
    imageVector: Int,
    contentDescId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {


    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id =imageVector ),
            contentDescription = stringResource(id = contentDescId),
            modifier = modifier,
            tint = Color.White
        )
    }

}