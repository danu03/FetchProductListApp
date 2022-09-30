package com.danusuhendra.fetchproductlistapp.model

import com.google.gson.annotations.SerializedName

data class Product(
    val category: String? = null,
    val description: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val price: Double? = null,
    val rating: RatingProduct? = null,
    val title: String? = null
)

data class RatingProduct(
    val count: Int? = null,
    val rate: Double? = null
)
