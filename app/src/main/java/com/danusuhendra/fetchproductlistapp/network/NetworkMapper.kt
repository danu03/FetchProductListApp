package com.danusuhendra.fetchproductlistapp.network

import com.danusuhendra.fetchproductlistapp.model.Product
import com.danusuhendra.fetchproductlistapp.model.ProductResponseItem
import com.danusuhendra.fetchproductlistapp.model.Rating
import com.danusuhendra.fetchproductlistapp.model.RatingProduct
import com.danusuhendra.fetchproductlistapp.utlis.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<ProductResponseItem, Product> {
    override fun mapFromEntity(product: ProductResponseItem): Product {
        val rating = RatingProduct(
            count = product.rating?.count,
            rate = product.rating?.rate
        )

        return Product(
            category = product.category,
            description = product.description,
            id = product.id,
            image = product.image,
            price = product.price,
            rating = rating,
            title = product.title
        )
    }

    override fun mapToEntity(product: Product): ProductResponseItem {
        val rating = Rating(
            count = product.rating?.count,
            rate = product.rating?.rate
        )
        return ProductResponseItem(
            category = product.category,
            description = product.description,
            id = product.id,
            image = product.image,
            price = product.price,
            rating = rating,
            title = product.title
        )
    }

    fun mapFromEntityList(product: List<ProductResponseItem>): List<Product> {
        return product.map { mapFromEntity(it) }
    }
}