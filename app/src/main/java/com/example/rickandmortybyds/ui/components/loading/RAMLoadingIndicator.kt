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

    Canvas(// lienzo de dibujo Compose
        modifier = modifier.size(100.dp)
    ) {

        rotate(rotation.value) {

            drawArc(
                color = primaryColor,
                startAngle = 0f,  // empieza a la derecha del circulo (arco)
                sweepAngle = 270f,      // se dibuja solo 270° del circulo
                useCenter = false,
                topLeft = Offset.Zero,      //el dibujo comienza en la esquina superior izquierda del Canvas
                size = size,
                style = Stroke(width = 8f)  // dibujo del borde del circulo
            )
        }
    }
}

