package com.faysal.dominoxandroid.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faysal.dominoxandroid.R
import com.faysal.dominoxandroid.ui.models.PIZZA_OPTIONS
import com.faysal.dominoxandroid.ui.theme.NUNITO
import com.faysal.dominoxandroid.ui.theme.PrimaryColor

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
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.Center)
                .wrapContentHeight()
        ) {

            PizzaSizeWidget(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .padding(horizontal = 80.dp),
                onSizeSelect = { size ->
                    // TODO: size
                }
            )

            PizzaWidget(
                modifier = Modifier.fillMaxWidth()
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
                shape = RoundedCornerShape(10.dp)
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
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.img_raw_pizza),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
            )
        }

        Row(
            modifier = Modifier
                .width(120.dp)
                .height(30.dp)
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
                    modifier = Modifier
                        .size(30.dp)
                )
            }

            Text(
                text = "1",
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
                    modifier = Modifier
                        .size(30.dp)
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
                    modifier = Modifier
                        .size(30.dp)
                )
            }


            LazyRow(
                modifier = Modifier.weight(0.6f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                itemsIndexed(PIZZA_OPTIONS) { _, item ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .padding(2.dp)
                            .border(
                                width = 3.dp,
                                color = PrimaryColor,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(item.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                        )
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
                    modifier = Modifier
                        .size(30.dp)
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
        mutableStateOf("S")
    }

    val sizeList = listOf("S", "M", "L")

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