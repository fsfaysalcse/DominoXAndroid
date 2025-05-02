package com.faysal.dominoxandroid.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faysal.dominoxandroid.R
import com.faysal.dominoxandroid.ui.models.PIZZA_OPTIONS
import com.faysal.dominoxandroid.ui.theme.NUNITO
import com.faysal.dominoxandroid.ui.theme.PrimaryColor
import com.faysal.dominoxandroid.ui.utility.dashedBorder
import com.faysal.dominoxandroid.ui.utility.getPizzaLayerImages
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CustomizePizza() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
    ) {

        CustomizePizzaToolbar(
            modifier = Modifier.fillMaxWidth(),
            onBackClick = {},
            onCartClick = {}
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .wrapContentHeight()
        ) {

            PizzaSizeWidget(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                onSizeSelect = {}
            )

            Spacer(Modifier.size(30.dp))

            PizzaWidget(
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.size(30.dp))

            PizzaSliceWidget(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                onSizeSelect = {}
            )
        }


        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface
                )
                .padding(
                    horizontal = 32.dp,
                    vertical = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val priceText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                ) {
                    append("Total : ".uppercase())
                }

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )
                ) {
                    append(" $12.30")
                }
            }

            Text(
                text = priceText,
                fontFamily = NUNITO,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .wrapContentWidth()
                    .height(50.dp),
                shape = CircleShape
            ) {
                Text(
                    text = "Apply",
                    fontFamily = NUNITO,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
            }
        }
    }


}

@Composable
fun PizzaWidget(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pathColor = MaterialTheme.colorScheme.surfaceVariant

        val selectedIndices = remember { mutableStateListOf(0, 1) }

        val context = LocalContext.current
        val basePizza = ImageBitmap.imageResource(R.drawable.img_raw_pizza)
        val pizzaLayers by remember {
            derivedStateOf {
                getPizzaLayerImages(context, selectedIndices)
            }
        }

        Canvas(modifier = Modifier.size(300.dp)) {
            val canvasSize = size.minDimension.toInt()
            val baseSize = (canvasSize * 1.05).toInt()
            val toppingSize = (canvasSize * 0.9).toInt()

            val baseDstOffset = IntOffset(
                ((size.width - baseSize) / 2).toInt(),
                ((size.height - baseSize) / 2).toInt()
            )
            val toppingDstOffset = IntOffset(
                ((size.width - toppingSize) / 2).toInt(),
                ((size.height - toppingSize) / 2).toInt()
            )

            drawIntoCanvas { canvas ->
                canvas.drawImageRect(
                    image = basePizza,
                    srcOffset = IntOffset.Zero,
                    srcSize = IntSize(basePizza.width, basePizza.height),
                    dstOffset = baseDstOffset,
                    dstSize = IntSize(baseSize, baseSize),
                    paint = Paint()
                )
                pizzaLayers.forEach { bitmap ->
                    val paint = Paint().apply { this.alpha = 0.8f }
                    canvas.drawImageRect(
                        image = bitmap,
                        srcOffset = IntOffset.Zero,
                        srcSize = IntSize(bitmap.width, bitmap.height),
                        dstOffset = toppingDstOffset,
                        dstSize = IntSize(toppingSize, toppingSize),
                        paint = paint
                    )
                }
            }

            val center = Offset(size.width / 2, size.height / 2)
            val radius = canvasSize / 2f
            val angleStep = 360f / 8
            val dashPathEffect = PathEffect.dashPathEffect(floatArrayOf(0f, 0f), 0f)

            for (i in 0 until 8) {
                val angle = Math.toRadians((i * angleStep).toDouble())
                val endX = center.x + radius * cos(angle).toFloat()
                val endY = center.y + radius * sin(angle).toFloat()
                drawLine(
                    color = pathColor,
                    start = center,
                    end = Offset(endX, endY),
                    strokeWidth = 25f,
                    pathEffect = dashPathEffect
                    )
                }
            }

        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .width(120.dp)
                .height(40.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    width = 4.dp,
                    color = PrimaryColor,
                    shape = CircleShape
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_remove),
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(40.dp)
                )
            }

            Text(
                text = "4",
                fontFamily = NUNITO,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black
            )

            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(Modifier.height(60.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(0.2f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
            }

            LazyRow(
                modifier = Modifier.weight(0.6f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                itemsIndexed(PIZZA_OPTIONS) { index, item ->
                    Box(
                        modifier = Modifier.size(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(CircleShape)
                                .border(3.dp, PrimaryColor, CircleShape)
                                .clickable {
                                    if (!selectedIndices.contains(index)) {
                                        selectedIndices.add(index)
                                    }
                                }
                                .padding(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(item.icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                            )
                        }

                        if (index in selectedIndices) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                tint = Color(0xFFF44336),
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .size(16.dp)
                                    .background(Color.White, shape = CircleShape)
                                    .padding(2.dp)
                                    .clickable {
                                        selectedIndices.remove(index)
                                    }
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.weight(0.2f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}


@Composable
fun PizzaSliceWidget(
    modifier: Modifier = Modifier,
    onSizeSelect: (String) -> Unit
) {

    val selectedSize by remember {
        mutableStateOf("8")
    }

    val sizeList = listOf("4", "6", "8", "12")

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        sizeList.forEachIndexed { _, name ->
            Box(
                modifier = Modifier
                    .size(60.dp, 40.dp)
                    .dashedBorder(
                        strokeWidth = 1.dp,
                        color = if (name == selectedSize) {
                            PrimaryColor
                        } else {
                            Color.Black
                        },
                        cornerRadiusDp = 9.dp
                    )
                    .clip(CircleShape)
                    .clickable {
                        onSizeSelect(selectedSize)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                    fontFamily = NUNITO,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = if (name == selectedSize) {
                        PrimaryColor
                    } else {
                        Color.Black
                    }
                )
            }
        }
    }
}


@Composable
fun PizzaSizeWidget(
    modifier: Modifier = Modifier,
    onSizeSelect: (String) -> Unit
) {

    val selectedSize by remember {
        mutableStateOf("L")
    }

    val sizeList = listOf("S", "M", "L", "XL")

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        sizeList.forEachIndexed { _, name ->
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape
                    )
                    .background(
                        color = if (name == selectedSize) {
                            Color(0xFFFF6701)
                        } else {
                            Color(0xFFFFFFFF)
                        },
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable {
                        onSizeSelect(selectedSize)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                    fontFamily = NUNITO,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = if (name == selectedSize) {
                        Color(0xFFFFFFFF)
                    } else {
                        Color(0xFF000000)
                    }
                )
            }
        }
    }
}

@Composable
fun CustomizePizzaToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCartClick: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    onBackClick()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }

            Text(
                text = "Customize",
                fontFamily = NUNITO,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            IconButton(
                onClick = {
                    onCartClick()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cart),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CustomizePizzaPreview() {
    CustomizePizza()
}