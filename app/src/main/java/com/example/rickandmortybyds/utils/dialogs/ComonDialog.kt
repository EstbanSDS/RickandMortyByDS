package com.example.rickandmortybyds.utils.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.rickandmortybyds.R

//DialogProperties(
//  dismissOnBackPress = true,         // Se cierra con el botón atrás (por defecto: true)
//  dismissOnClickOutside = false,     // Se cierra al tocar fuera del diálogo (por defecto: true)
//  usePlatformDefaultWidth = false    // Permite que el diálogo tome el ancho completo o personalizado
//)

@Composable
fun AlertCommonDialog(
    showAlertDialog: Boolean = false,
    title: String,
    message: String,
    dialogProperties: DialogProperties = DialogProperties(),
    textOnAccept: String = "Aceptar",
    onAccept: () -> Unit,
    textOnDismiss: String = "",
    onDismiss: () -> Unit,
) {
    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(title)
                Spacer(modifier = Modifier.height(50.dp))
            },

            text = {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.dog_error),
                        contentDescription = "Imagen de error (perrito triste)",
                        modifier = Modifier
                            .size(180.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(message)
                }
            },

            confirmButton = {
                TextButton(onClick = { onAccept() }) {
                    Text(textOnAccept)
                }
            },
            dismissButton = {
                if (textOnDismiss.isNotBlank()) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(textOnDismiss)
                    }
                }
            },
            properties = dialogProperties
        )
    }
}

@Composable
fun CommonCustomdialog(
    showDialog: Boolean,
    textTitle: String = "",
    textBody: String = "",
    textOnAccept: String = "",
    onAccept: () -> Unit,
    textOnCancel: String = "",
    onCancel: () -> Unit,
    textOnNeutral: String = "",
    onNeutral: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = textTitle,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = textBody,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // 3 Botones alineados verticalmente para evitar desbordes
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onAccept,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(textOnAccept)
                        }

                        OutlinedButton(
                            onClick = onNeutral,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(textOnNeutral)
                        }

                        TextButton(
                            onClick = onCancel,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(textOnCancel, color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}