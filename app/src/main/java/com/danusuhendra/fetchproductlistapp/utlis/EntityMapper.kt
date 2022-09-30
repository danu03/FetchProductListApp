package com.danusuhendra.fetchproductlistapp.utlis

interface EntityMapper<E, D> {

    fun mapFromEntity(e: E): D

    fun mapToEntity(d: D): E
}