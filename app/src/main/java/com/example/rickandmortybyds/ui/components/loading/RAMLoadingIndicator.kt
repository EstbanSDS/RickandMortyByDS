package com.example.rickandmortybyds.ui.components.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

@Composable
fun RAMLoadingIndicator(
    modifier: Modifier = Modifier
) {

    val primaryColor = MaterialTheme.colorScheme.primary

    val infiniteTransition = rememberInfiniteTransition(        // encargado de actualizar los valores de las animaciones
        label = "Loading Rotation"
    )

    val rotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "Rotation"
    )

    val innerRotation = infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1800,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "Inner Rotation"
    )

    // ARCO DINAMICO
   /* val sweepAngle = infiniteTransition.animateFloat(
        initialValue = 60f,     // inicia con arco pequeño
        targetValue = 300f,     // después crece casi tod0 el círculo
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 900,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Sweep Angle"
    )
*/
    Canvas( // lienzo de dibujo Compose
        modifier = modifier.size(100.dp)
    ) {

        rotate(rotation.value) {

            drawArc(
                color = primaryColor,
                startAngle = 0f,
                sweepAngle = 70f,
                useCenter = false,
                topLeft = Offset.Zero,      // el dibujo comienza en la esquina superior izquierda del Canvas
                size = size,
                style = Stroke(width = 8f)  // dibujo del borde del círculo
            )

            drawArc(
                color = primaryColor.copy(alpha = 0.85f),
                startAngle = 95f,
                sweepAngle = 35f,
                useCenter = false,
                topLeft = Offset.Zero,
                size = size,
                style = Stroke(width = 6f)
            )

            drawArc(
                color = primaryColor.copy(alpha = 0.65f),
                startAngle = 155f,
                sweepAngle = 60f,
                useCenter = false,
                topLeft = Offset.Zero,
                size = size,
                style = Stroke(width = 7f)
            )

            drawArc(
                color = primaryColor.copy(alpha = 0.80f),
                startAngle = 240f,
                sweepAngle = 28f,
                useCenter = false,
                topLeft = Offset.Zero,
                size = size,
                style = Stroke(width = 5f)
            )

            drawArc(
                color = primaryColor.copy(alpha = 0.90f),
                startAngle = 295f,
                sweepAngle = 45f,
                useCenter = false,
                topLeft = Offset.Zero,
                size = size,
                style = Stroke(width = 8f)
            )
        }

        rotate(innerRotation.value) {

            drawArc(
                color = primaryColor.copy(alpha = 0.55f),
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(
                    x = size.width * 0.18f,
                    y = size.height * 0.18f
                ),
                size = Size(
                    width = size.width * 0.64f,
                    height = size.height * 0.64f
                ),
                style = Stroke(width = 6f)
            )
        }
    }
}