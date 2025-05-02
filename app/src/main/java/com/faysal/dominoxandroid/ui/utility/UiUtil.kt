package com.faysal.dominoxandroid.ui.utility

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.core.content.ContextCompat
import com.faysal.dominoxandroid.R

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)

fun Context.getImageBitmapFromDrawable(@DrawableRes resId: Int): ImageBitmap {
    val drawable = ContextCompat.getDrawable(this, resId)!!
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth.coerceAtLeast(1),
        drawable.intrinsicHeight.coerceAtLeast(1),
        Bitmap.Config.ARGB_8888
    )
    val canvas = android.graphics.Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap.asImageBitmap()
}

fun getPizzaLayerImages(context: Context, selectedIndices: List<Int>): List<ImageBitmap> {
   // val layerOrder = listOf(0, 1, 2, 3, 4)
    val layerOrder = listOf(0, 2, 1)
    val resourceMap = mapOf(
        0 to R.drawable.img_cheese_pizza,
        1 to R.drawable.img_mushroom_pizza,
        2 to R.drawable.img_pepperoni_pizza,
        /*3 to R.drawable.img_veggie_pizza,
        4 to R.drawable.img_bbq_chicken_pizza*/
    )
    return layerOrder.filter { selectedIndices.contains(it) }
        .mapNotNull { resourceMap[it] }
        .map { ImageBitmap.imageResource(context.resources, it) }
}