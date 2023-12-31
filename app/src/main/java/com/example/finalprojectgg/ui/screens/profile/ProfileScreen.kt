package com.example.finalprojectgg.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.finalprojectgg.service.DisasterNotificationService
import com.example.finalprojectgg.ui.components.SheetContentLocationPicker
import com.example.finalprojectgg.ui.components.SheetLocationPicker
import com.example.finalprojectgg.ui.screens.profile.componentes.InformationDialogView
import com.example.finalprojectgg.ui.screens.profile.componentes.ThemeSwitcher
import com.example.finalprojectgg.ui.screens.profile.state.ProfileScreenEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.example.finalprojectgg.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var notificationDialogState by remember { mutableStateOf(false) }
    var locationDialogState by remember { mutableStateOf(false) }
    val themeState by viewModel.themeState
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()
    val notificationService = DisasterNotificationService(context)
    var notificationState by remember { mutableStateOf(false) }
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val sheetState =
            rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val corutineScope = rememberCoroutineScope()
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            ) {
                AsyncImage(
                    model = "https://images.unsplash.com/photo-1530893609608-32a9af3aa95c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=764&q=80",
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                )

                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://images.unsplash.com/photo-1504593811423-6dd665756598?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80",
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Mubariz Mehdizadeh",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Column(
                Modifier
                    .padding(horizontal = 24.dp)
                    .graphicsLayer {
                        translationY = -100f
                    }) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(all = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                corutineScope.launch {
                                    sheetState.show()
                                }
                            }) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Default Location",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                IconButton(onClick = { locationDialogState = true }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = null
                                    )
                                }
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowRight,
                                    contentDescription = null
                                )
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.outline)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DarkMode,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Theme",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                            ThemeSwitcher(
                                darkTheme = themeState,
                                size = 40.dp,
                                onClick = {
                                    viewModel.onProfileScreenEvent(ProfileScreenEvent.ThemeSwitchChanged)
                                }
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.outline)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DarkMode,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Notification",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                IconButton(onClick = { notificationDialogState = true }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = null
                                    )
                                }
                            }
                            Switch(
                                checked = notificationState,
                                onCheckedChange = {
                                    if (it) {
                                        notificationService.showNotification(
                                            "Early Warning",
                                            "Bendungan Katulampa water level increase to level III"
                                        )
                                    }
                                    notificationState = it
                                })
                        }
                    }
                }
            }
        }
        SheetLocationPicker(
            sheetState = sheetState,
            filterState = filterState,
            onItemClick = {}
        )
    }
    NotificationDialog(
        notificationState = notificationDialogState,
        onDismiss = { notificationDialogState = false },
        sendNotificationButton = {
            notificationService.showNotification(
                "Early Warning",
                "Bendungan Katulampa water level increase to level III"
            )
        })

    DefaultLocationDialog(locationState = locationDialogState) {
        locationDialogState = false
    }
}

@Composable
fun NotificationDialog(
    notificationState: Boolean,
    onDismiss: () -> Unit,
    sendNotificationButton: () -> Unit,
) {
    InformationDialogView(
        title = "Notification", icon = {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null
            )
        },
        text = "This is should be setting for notification, whether it's active or not. " +
                "But unfortunately our API currently not working. You can try send notification to simulate that behavior  ",
        informationDialogState = notificationState,
        confirmButton = {
            Button(onClick = { sendNotificationButton() }) {
                Text(text = "Try Send Notification")
            }
        },
        onDismiss = onDismiss
    )
}

@Composable
fun DefaultLocationDialog(
    locationState: Boolean,
    onDismiss: () -> Unit
) {
    InformationDialogView(
        title = "Default Location",
        icon = { Icon(imageVector = Icons.Filled.PinDrop, contentDescription = null) },
        text = "Automatically use filter by default location when opening app",
        informationDialogState = locationState,
        confirmButton = { /*TODO*/ }, onDismiss = onDismiss
    )
}