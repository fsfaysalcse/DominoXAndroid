package com.faysal.dominoxandroid.ui.models

import com.faysal.dominoxandroid.R

data class PizzaOption(
    val name: String,
    val icon: Int
)

val PIZZA_OPTIONS = listOf(
    PizzaOption("Cheese", R.drawable.ic_cheese),
    PizzaOption("Mushroom", R.drawable.ic_mushroom),
    PizzaOption("Pepperoni", R.drawable.ic_pepperoni),
    PizzaOption("Veggie", R.drawable.ic_veggie),
    PizzaOption("BBQ Chicken", R.drawable.ic_bbq_chicken)
)