package com.example.rickandmortybyds.utils.dialogs


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rickandmortybyds.R


@Composable
fun CommonDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
) {
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
            TextButton(onClick = { onDismiss() }) {
                Text("Aceptar")
            }
        },

        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        }
    )
}